package com.huasheng.dingding.common.CallInUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class CallInOperate implements CallInStrategy{

    @Resource
    private ClockInMapper clockInMapper;

    @Resource
    private DingTalkUtils dingTalkUtils;


    @Override
    public Result<String> callInStrategy(String userName, String userId, String type, String location, String project, String note) {
        QueryWrapper<ClockIn> last = new QueryWrapper<ClockIn>()
                .eq("user_name", userName)
                .eq("clock_date", DateUtils.getStringDateShort())
//                .eq("project", project)
                .orderByDesc("id")
                .last("limit 1");
        ClockIn clock = clockInMapper.selectOne(last);
        if (clock != null && StringUtils.isBlank(clock.getKnockOffTime())) {
            return ResultUtils.ERROR("项目："+clock.getProject()+"未下班");
        }
        if (clock != null && clock.getProject().equals("非项目")) {
            return ResultUtils.ERROR("非项目上班卡已打，无需重复操作");
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
            clockIn.setLocation(location);
            clockIn.setNote(note);
            clockIn.setType(type);
            clockIn.setUserName(userName);
            clockIn.setProject(project);
            clockIn.setTitle(title);
            clockIn.setClockInTime(DateUtils.getStringDate());
            // 非项目迟到则执行
            if (DateUtils.isLate() && project.equals("非项目")) {
                clockIn.setLateSituation(DateUtils.calcLateTime());
            }
            int insert = clockInMapper.insert(clockIn);
            if (insert > 0) {
                return ResultUtils.SUCCESS();
            }
            return ResultUtils.ERROR("打卡失败");
    }
}
