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

@Service
public class KnockOffOperate implements CallInStrategy{

    @Resource
    private ClockInMapper clockInMapper;

    @Override
    public Result<String> callInStrategy(String userName, String userId, String type, String location, String project, String note) {

        QueryWrapper<ClockIn> last = new QueryWrapper<ClockIn>().eq("user_name", userName)
                .eq("clock_date", DateUtils.getStringDateShort())
                .eq("project", project)
                .orderByDesc("id")
                .last("limit 1");
        ClockIn checkClockRecord = clockInMapper.selectOne(last);
        if (checkClockRecord == null || StringUtils.isNotBlank(checkClockRecord.getKnockOffTime())) {
            return ResultUtils.ERROR("请先对该项目打卡上班");
        }
        UpdateWrapper<ClockIn> set = new UpdateWrapper<ClockIn>().eq("id", checkClockRecord.getId())
                .set("note",checkClockRecord.getNote())
                .set("knock_off_time", DateUtils.getStringDate());
        int update = clockInMapper.update(null, set);
        if (update >0) {
            return ResultUtils.SUCCESS();
        }
        return ResultUtils.ERROR("打卡失败");
    }
}
