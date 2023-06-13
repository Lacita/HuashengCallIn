package com.huasheng.dingding.service.Imp;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.CallInUtils.CallInStrategy;
import com.huasheng.dingding.common.CallInUtils.CallInStrategyFactory;
import com.huasheng.dingding.common.Constant.RedisConstant;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.DingTalkUtils;
import com.huasheng.dingding.config.RedisUtils;
import com.huasheng.dingding.config.TokenUtils;
import com.huasheng.dingding.domain.dto.ClockQueryDto;
import com.huasheng.dingding.domain.dto.LoginUser;
import com.huasheng.dingding.domain.entity.CallInProject;
import com.huasheng.dingding.domain.entity.ClockIn;
import com.huasheng.dingding.domain.entity.ExcelEntity;
import com.huasheng.dingding.mapper.ClockInMapper;
import com.huasheng.dingding.mapper.ClockInProjectMapper;
import com.huasheng.dingding.mapper.DdMapper;
import com.huasheng.dingding.service.DdService;
import com.huasheng.dingding.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DdServiceImp implements DdService {

    @Resource
    private DdMapper ddMapper;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private DingTalkUtils dingTalkUtils;

    @Resource
    private ClockInMapper clockInMapper;

    @Resource
    private ClockInProjectMapper clockInProjectMapper;

    @Resource
    private TaskExecutor taskExecutor;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private final ApplicationContext applicationContext;

    public DdServiceImp(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Result<String> login(String userName, String password) {
        QueryWrapper<LoginUser> loginUserQueryWrapper = new QueryWrapper<>();
        loginUserQueryWrapper.eq("user_name",userName);
        String desEncrypt = PasswordUtil.desEncrypt(password);
        loginUserQueryWrapper.eq("password",desEncrypt);
        LoginUser loginUser = ddMapper.selectOne(loginUserQueryWrapper);
        if (loginUser == null) {
            return ResultUtils.ERROR("账号密码错误");
        }
        int status = loginUser.getStatus();
        if (status != 1){
            return ResultUtils.ERROR("账户已禁用");
        }
        String sign = TokenUtils.sign(loginUser.getUserName());
        return ResultUtils.SUCCESS_DATA(sign);
    }

    @Override
    @Transactional
    public Result<String> clockInMessage(String userName, String userId, String type, String location, String project,String note,double longitude,double latitude) {
        // 判断是否处于打卡范围,以及是否是外勤类型
        if (!this.checkIfDistance(longitude,latitude,userId) &&
                !(type.equals(RedisConstant.FIELD_CALL_IN_TYPE)||type.equals(RedisConstant.FIELD_KNOCK_TYPE)) ) {
            return ResultUtils.ERROR("不在打卡范围内");
        }
        // 后端处理防抖
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(RedisConstant.REDIS_KEY + userId + type, userId, 15L, TimeUnit.SECONDS);
        if (BooleanUtil.isFalse(ifAbsent)){
            return ResultUtils.ERROR("请15秒后尝试");
        }
        // 判断打卡类型
        try{
            String beanType = RedisConstant.TYPE.get(type);
            // 上下文获取bean
            CallInStrategy bean = applicationContext.getBean(beanType, CallInStrategy.class);
            return bean.callInStrategy(userName,userId,type,location,project,note);
        }catch (Exception e){
            throw new MyException(e.toString());
        }
    }

    @Override
    public Result<Map<String, Object>> getUserInfo(String code) {
        if (StringUtils.isBlank(code)) {
            return ResultUtils.ERROR(null);
        }
        // 开启异步获取用户以及打卡信息
        String accessToken = dingTalkUtils.getAccessToken();
        CompletableFuture<Map<String, Object>> getUserInfoTask = CompletableFuture.supplyAsync(() -> {
            Map<String, Object> userinfo = dingTalkUtils.getUserinfo(accessToken, code);
            return userinfo;
        },taskExecutor);
        CompletableFuture<List<String>> getCallInProjectTask = CompletableFuture.supplyAsync(() -> {
            QueryWrapper<CallInProject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_status",1);
            List<CallInProject> callInProjects = clockInProjectMapper.selectList(queryWrapper);
            List<String> collect = callInProjects.stream().map(CallInProject::getProjectName).collect(Collectors.toList());
            return collect;
        },taskExecutor);
        CompletableFuture.allOf(getUserInfoTask,getCallInProjectTask);
        try {
            Map<String, Object> userInfo = getUserInfoTask.get();
            List<String> callInProjects = getCallInProjectTask.get();
            userInfo.put("project",callInProjects);
            return ResultUtils.SUCCESS_DATA(userInfo);
        }
        catch (Exception e){
            log.error("小程序用户初始化信息异常:{}", e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Result<Map<String, Object>> getClockInRecord(ClockQueryDto clockQueryDto) {
        String dept = clockQueryDto.getDept();
        String endTime = clockQueryDto.getEndTime();
        String startTime = clockQueryDto.getStartTime();
        String title = clockQueryDto.getTitle();
        String userName = clockQueryDto.getUserName();
        String type = clockQueryDto.getType();
        int startPage = clockQueryDto.getStartPage();
        int size = clockQueryDto.getSize();
        QueryWrapper<ClockIn> clockInQueryWrapper = new QueryWrapper<>();
        clockInQueryWrapper.like(StringUtils.isNotBlank(dept),"dept",dept);
        clockInQueryWrapper.like(StringUtils.isNotBlank(userName),"user_name",userName);
        clockInQueryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        clockInQueryWrapper.ge(StringUtils.isNotBlank(startTime),"clock_date",startTime);
        clockInQueryWrapper.le(StringUtils.isNotBlank(endTime),"clock_date",endTime);
        clockInQueryWrapper.eq(StringUtils.isNotBlank(type),"type",type);
        clockInQueryWrapper.notIn("project","非项目");
        clockInQueryWrapper.orderByDesc("clock_in_time");
        try {
//            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Page<ClockIn> clockInPage = new Page<>(startPage, size);
//            IPage<ClockIn> clockInIPage = clockInMapper.selectPage(clockInPage, clockInQueryWrapper);
            Page<ClockIn> clockInIPage = clockInMapper.selectByCallInCondition(clockInPage, clockInQueryWrapper);
            List<ClockIn> records = clockInIPage.getRecords();
            long total = clockInIPage.getTotal();
            Map<String,Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }
        catch (Exception e){
            log.error("报表查询异常，本次异常代码为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void exportCallInRecord(ClockQueryDto clockQueryDto, HttpServletResponse response) throws IOException {
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx" );
        EasyExcel.write(response.getOutputStream(), ExcelEntity.class)
                .sheet("打卡记录").doWrite(getCallInRecordByCondition(clockQueryDto));
    }

    @Override
    public Result<Map<String, Object>> showProject(String projectId,String projectName, Integer page, Integer size) {
        QueryWrapper<CallInProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(projectId),"project_id",projectId);
        queryWrapper.like(StringUtils.isNotBlank(projectName),"project_name",projectName);
        Page<CallInProject> callInProjectPage = new Page<>(page, size);
        try{
            IPage<CallInProject> callInProjectIPage = clockInProjectMapper.selectPage(callInProjectPage, queryWrapper);
            List<CallInProject> records = callInProjectIPage.getRecords();
            long total = callInProjectIPage.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }
        catch (Exception e){
            log.error("打卡项目查询异常,异常代码为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Result<String> addNewProject(String projectId, String projectName) {
        if (StringUtils.isBlank(projectId) || StringUtils.isBlank(projectName)) {
            return ResultUtils.ERROR("项目属性非法");
        }
        CallInProject callInProject = new CallInProject();
        callInProject.setProjectId(projectId);
        callInProject.setProjectName(projectName);
        int insert = clockInProjectMapper.insert(callInProject);
        if (insert > 0){
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("插入失败");
    }

    @Override
    @Transactional
    public Result<String> updateProject(Long id) {
        boolean update = clockInProjectMapper.upProjectStatus(id);
        if (update){
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("调整失败");
    }

    @Override
    public Result<Map<String, Object>> getClockInRecordWithNoProject(ClockQueryDto clockQueryDto) {
        String dept = clockQueryDto.getDept();
        String endTime = clockQueryDto.getEndTime();
        String startTime = clockQueryDto.getStartTime();
        String title = clockQueryDto.getTitle();
        String userName = clockQueryDto.getUserName();
        String type = clockQueryDto.getType();
        int startPage = clockQueryDto.getStartPage();
        int size = clockQueryDto.getSize();
//        int startPage = start -1 <= 0? 0:(start -1)*size;
        QueryWrapper<ClockIn> clockInQueryWrapper = new QueryWrapper<>();
        clockInQueryWrapper.like(StringUtils.isNotBlank(dept),"dept",dept);
        clockInQueryWrapper.like(StringUtils.isNotBlank(userName),"user_name",userName);
        clockInQueryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        clockInQueryWrapper.ge(StringUtils.isNotBlank(startTime),"clock_date",startTime);
        clockInQueryWrapper.le(StringUtils.isNotBlank(endTime),"clock_date",endTime);
        clockInQueryWrapper.eq(StringUtils.isNotBlank(type),"type",type);
        clockInQueryWrapper.eq("project","非项目");
//        clockInQueryWrapper.groupBy("user_name");
        clockInQueryWrapper.groupBy("user_name","clock_date").select("user_name","clock_date");
        clockInQueryWrapper.orderByAsc("clock_in_time");
        clockInQueryWrapper.orderByDesc("knock_off_time");
        try {
            Page<ClockIn> clockInPage = new Page<>(startPage, size);
            IPage<ClockIn> clockInIPage = clockInMapper.selectByCallInConditionWithNoProject(clockInPage, clockInQueryWrapper);
//            Page<ClockIn> clockInIPage = clockInMapper.selectByCallInConditionWithNoProject(clockInPage, clockInQueryWrapper);
            List<ClockIn> records = clockInIPage.getRecords();
            long total = clockInIPage.getTotal();
//            List<ClockIn> records = clockInMapper.selectByCallInConditionWithNoProject(dept, userName, title, startTime, endTime, type, startPage, size);
//            long total = clockInMapper.selectByCallInConditionWithNoProjectCount(dept, userName, title, startTime, endTime, type);
            Map<String,Object> map = new HashMap<>();
            map.put("data",records);
            map.put("total",total);
            return ResultUtils.SUCCESS_DATA(map);
        }
        catch (Exception e){
            log.error("报表查询异常，本次异常代码为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void exportWithNoProject(ClockQueryDto clockQueryDto, HttpServletResponse response) throws IOException {
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx" );
        EasyExcel.write(response.getOutputStream(), ExcelEntity.class)
                .sheet("打卡记录").doWrite(getCallInRecordWithNoProject(clockQueryDto));
    }

    /***
     * 获取非项目打卡信息
     */
    private List<ExcelEntity> getCallInRecordWithNoProject(ClockQueryDto clockQueryDto){
        String dept = clockQueryDto.getDept();
        String endTime = clockQueryDto.getEndTime();
        String startTime = clockQueryDto.getStartTime();
        String title = clockQueryDto.getTitle();
        String userName = clockQueryDto.getUserName();
        String type = clockQueryDto.getType();
        QueryWrapper<ClockIn> clockInQueryWrapper = new QueryWrapper<>();
        clockInQueryWrapper.like(StringUtils.isNotBlank(dept),"dept",dept);
        clockInQueryWrapper.like(StringUtils.isNotBlank(userName),"user_name",userName);
        clockInQueryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        clockInQueryWrapper.ge(StringUtils.isNotBlank(startTime),"clock_date",startTime);
        clockInQueryWrapper.le(StringUtils.isNotBlank(endTime),"clock_date",endTime);
        clockInQueryWrapper.eq(StringUtils.isNotBlank(type),"type",type);
        clockInQueryWrapper.eq("project","非项目");
//        clockInQueryWrapper.groupBy("user_name");
        clockInQueryWrapper.groupBy("user_name","clock_date").select("user_name","clock_date");
        clockInQueryWrapper.orderByAsc("clock_in_time");
        clockInQueryWrapper.orderByDesc("knock_off_time");
        List<ExcelEntity> excelParamList = Lists.newArrayList();
        try{
            List<ClockIn> clockIns = clockInMapper.exportByCallInCondition(clockInQueryWrapper);
//            List<ClockIn> clockIns = clockInMapper.selectList(clockInQueryWrapper);
            if (!CollectionUtils.isEmpty(clockIns)) {
                clockIns.forEach(item ->{
                    ExcelEntity excelEntity = new ExcelEntity();
                    excelEntity.setClockDate(String.valueOf(item.getClockDate()));
                    excelEntity.setDept(item.getDept());
                    excelEntity.setLocation(item.getLocation());
                    excelEntity.setNote(item.getNote());
                    excelEntity.setProject(item.getProject());
                    excelEntity.setTitle(item.getTitle());
                    excelEntity.setType(item.getCallInType());
                    excelEntity.setJobNumber(item.getJobNumber());
                    excelEntity.setClockInTime(item.getClockInTime());
                    excelEntity.setUserName(item.getUserName());
                    excelEntity.setKnockOffTime(item.getKnockOffTime());
                    excelEntity.setOverTime(item.getOverTime());
                    excelEntity.setOverTimeEnd(item.getOverTimeEnd());
                    excelEntity.setFieldLocation(item.getFieldLocation());
                    excelEntity.setFieldKnockLocation(item.getFieldKnockLocation());
                    excelEntity.setWorkTimeResult(item.getWorkTimeResult());
                    excelEntity.setOverTimeResult(item.getOverTimeResult());
                    excelEntity.setLateSituation(item.getLateSituation());
                    excelEntity.setEarlySituation(item.getEarlySituation());
                    excelParamList.add(excelEntity);
                });
            }
            return excelParamList;
        }
        catch (Exception e){
            log.error("数据查询异常,{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }


    /***
     * 获取项目打卡信息
     * @param clockQueryDto
     * @return
     */
    private List<ExcelEntity> getCallInRecordByCondition(ClockQueryDto clockQueryDto){
        String dept = clockQueryDto.getDept();
        String endTime = clockQueryDto.getEndTime();
        String startTime = clockQueryDto.getStartTime();
        String title = clockQueryDto.getTitle();
        String userName = clockQueryDto.getUserName();
        String type = clockQueryDto.getType();
        QueryWrapper<ClockIn> clockInQueryWrapper = new QueryWrapper<>();
        clockInQueryWrapper.like(StringUtils.isNotBlank(dept),"dept",dept);
        clockInQueryWrapper.like(StringUtils.isNotBlank(userName),"user_name",userName);
        clockInQueryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        clockInQueryWrapper.ge(StringUtils.isNotBlank(startTime),"clock_date",startTime);
        clockInQueryWrapper.le(StringUtils.isNotBlank(endTime),"clock_date",endTime);
        clockInQueryWrapper.eq(StringUtils.isNotBlank(type),"type",type);
        clockInQueryWrapper.notIn("project","非项目");
        clockInQueryWrapper.orderByDesc("clock_in_time");
        List<ExcelEntity> excelParamList = Lists.newArrayList();
        try{
            List<ClockIn> clockIns = clockInMapper.exportByCallInCondition(clockInQueryWrapper);
//            List<ClockIn> clockIns = clockInMapper.selectList(clockInQueryWrapper);
            if (!CollectionUtils.isEmpty(clockIns)) {
                clockIns.forEach(item ->{
                    ExcelEntity excelEntity = new ExcelEntity();
                    excelEntity.setClockDate(String.valueOf(item.getClockDate()));
                    excelEntity.setDept(item.getDept());
                    excelEntity.setLocation(item.getLocation());
                    excelEntity.setNote(item.getNote());
                    excelEntity.setProject(item.getProject());
                    excelEntity.setTitle(item.getTitle());
                    excelEntity.setType(item.getCallInType());
                    excelEntity.setJobNumber(item.getJobNumber());
                    excelEntity.setClockInTime(item.getClockInTime());
                    excelEntity.setUserName(item.getUserName());
                    excelEntity.setKnockOffTime(item.getKnockOffTime());
                    excelEntity.setOverTime(item.getOverTime());
                    excelEntity.setOverTimeEnd(item.getOverTimeEnd());
                    excelEntity.setFieldLocation(item.getFieldLocation());
                    excelEntity.setFieldKnockLocation(item.getFieldKnockLocation());
                    excelEntity.setWorkTimeResult(item.getWorkTimeResult());
                    excelEntity.setOverTimeResult(item.getOverTimeResult());
                    excelParamList.add(excelEntity);
                });
            }
            return excelParamList;
        }
        catch (Exception e){
            log.error("数据查询异常,{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    /**
     * 根据经纬度判断是否在符合范围区间
     * @param longitude 传入打卡人员当前精度
     * @param latitude 传入打卡人员当前纬度
     * @return 返回true或者false
     */
    private boolean checkIfDistance(double longitude,double latitude,String userId){
        if (longitude < 0 || latitude < 0){
            return false;
        }
//        double distance = redisUtils.getDistance(longitude, latitude,userId);
//        double aBaoDistance = redisUtils.getABaoDistance(longitude, latitude,userId);
//        return !(distance > RedisConstant.LIMIT_DISTANCE || aBaoDistance >RedisConstant.LIMIT_DISTANCE);
//        return !(distance > RedisConstant.LIMIT_DISTANCE) && !(aBaoDistance > RedisConstant.LIMIT_DISTANCE);
//        if (distance > RedisConstant.LIMIT_DISTANCE )
        return redisUtils.getLocationGeoRadius(longitude,latitude,userId);
    }
}
