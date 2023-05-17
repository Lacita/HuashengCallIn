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
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class FieldTypeOperate implements CallInStrategy{

    @Resource
    private ClockInMapper clockInMapper;

    @Resource
    private DingTalkUtils dingTalkUtils;


    @Override
    public Result<String> callInStrategy(String userName, String userId, String type, String location, String project, String note) {
        if (!project.equals("外勤")) {
            return ResultUtils.ERROR("打卡项目请选外勤打卡");
        }
        QueryWrapper<ClockIn> last = new QueryWrapper<ClockIn>().eq("user_name", userName)
                .eq("clock_date", DateUtils.getStringDateShort())
                .eq("type",3)
                .orderByDesc("id")
                .last("limit 1");
        ClockIn checkClockRecord = clockInMapper.selectOne(last);
        if (checkClockRecord != null && StringUtils.isNotBlank(checkClockRecord.getClockInTime())) {
            return ResultUtils.ERROR("已外勤打卡，无需重复打上班卡");
        }
            // 判断accessKey是否过期，并赋值使用
            String accessKey = dingTalkUtils.accessKeyExpire();
            Map<String, Object> details = dingTalkUtils.getUserDetails(accessKey, userId);
            String title = (String)details.get("title");
            String jobNumber = (String)details.get("jobNumber");
            List<Long> deptIdList = (List<Long>)details.get("deptIdList");
            ClockIn clockIn = new ClockIn();
            if(deptIdList == null || deptIdList.size() == 0){
                clockIn.setDept("");
            }
            Long aLong = deptIdList.get(0);
            clockIn.setDept(dingTalkUtils.getDeptDetails(accessKey,aLong));
            clockIn.setClockDate(Date.valueOf(LocalDate.now()));
            clockIn.setJobNumber(jobNumber);
            clockIn.setFieldLocation(location);
            clockIn.setNote(note);
            clockIn.setType(type);
            clockIn.setUserName(userName);
            clockIn.setProject(project);
            clockIn.setTitle(title);
            clockIn.setClockInTime(DateUtils.getStringDate());
            int insert = clockInMapper.insert(clockIn);
            if (insert >0){
                return ResultUtils.SUCCESS();
            }
            return ResultUtils.ERROR("打卡失败");
        }
}
