package com.huasheng.dingding.common.CallInUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.DateUtils;
import com.huasheng.dingding.config.DingTalkUtils;
import com.huasheng.dingding.domain.entity.ClockIn;
import com.huasheng.dingding.mapper.ClockInMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class OverTimeOperate implements CallInStrategy{

    @Resource
    private ClockInMapper clockInMapper;

    @Resource
    private DingTalkUtils dingTalkUtils;

    // 加班打卡逻辑
    @Override
    public Result<String> callInStrategy(String userName, String userId, String type,
                                             String location, String project,String note){

        if (DateUtils.isOverTime()){
            return ResultUtils.ERROR("未到加班时间");
        }
        // 条件查询判断用户是否对该项目有正常上班逻辑
        QueryWrapper<ClockIn> overTimeWrapper = new QueryWrapper<ClockIn>()
                .eq("user_name", userName)
                .eq("project",project)
                .eq("clock_date", DateUtils.getStringDateShort())
                .orderByDesc("id")
                .last("limit 1");
        ClockIn clockIn = clockInMapper.selectOne(overTimeWrapper);
        // 判断今天是否有该项目的正常上班记录
        if (clockIn != null && StringUtils.isBlank(clockIn.getOverTime())) {
            UpdateWrapper<ClockIn> clockInUpdateWrapper = new UpdateWrapper<ClockIn>().set("over_time", DateUtils.getStringDate())
                    .eq("id", clockIn.getId());
            int update = clockInMapper.update(null, clockInUpdateWrapper);
            if (update > 0){
                return ResultUtils.SUCCESS();
            }
            return ResultUtils.ERROR("加班打卡失败");
        }
        // 判断是否已经存在加班上班的打卡记录，有则为打下班卡
        if (clockIn != null && StringUtils.isNotBlank(clockIn.getOverTime()) && StringUtils.isBlank(clockIn.getOverTimeEnd())) {
            UpdateWrapper<ClockIn> clockInUpdateWrapper = new UpdateWrapper<ClockIn>()
                    .set("over_time_end", DateUtils.getStringDate())
                    .set("over_time_result",DateUtils.getDiffTime(clockIn.getClockInTime(),DateUtils.getStringDate()))
                    .eq("id", clockIn.getId());
            int update = clockInMapper.update(null, clockInUpdateWrapper);
            if (update > 0){
                return ResultUtils.SUCCESS();
            }
            return ResultUtils.ERROR("加班打卡失败");
        }
        // 获取用户当前信息
        String accessKey = dingTalkUtils.accessKeyExpire();
        Map<String, Object> details = dingTalkUtils.getUserDetails(accessKey, userId);
        String title = (String)details.get("title");
        String jobNumber = (String)details.get("jobNumber");
        List<Long> deptIdList = (List<Long>)details.get("deptIdList");
        // 无该项目正常打卡记录
        ClockIn overTimeCallIn = new ClockIn();
        if(deptIdList == null || deptIdList.size() == 0){
            overTimeCallIn.setDept("");
        }
        Long depId = deptIdList.get(0);
        overTimeCallIn.setDept(dingTalkUtils.getDeptDetails(accessKey,depId));
        overTimeCallIn.setClockDate(Date.valueOf(LocalDate.now()));
        overTimeCallIn.setJobNumber(jobNumber);
        overTimeCallIn.setLocation(location);
        overTimeCallIn.setNote(note);
        overTimeCallIn.setType(type);
        overTimeCallIn.setUserName(userName);
        overTimeCallIn.setProject(project);
        overTimeCallIn.setTitle(title);
        overTimeCallIn.setClockInTime(null);
        overTimeCallIn.setKnockOffTime(null);
        overTimeCallIn.setOverTime(DateUtils.getStringDate());
        int insert = clockInMapper.insert(overTimeCallIn);
        if (insert > 0){
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("加班打卡失败");
    }

}
