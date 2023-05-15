package com.huasheng.dingding.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huasheng.dingding.common.CallInUtils.CallInOperate;
import com.huasheng.dingding.common.CallInUtils.KnockOffOperate;
import com.huasheng.dingding.config.DateUtils;
import com.huasheng.dingding.config.RedisUtils;
import com.huasheng.dingding.domain.entity.ClockIn;
import com.huasheng.dingding.mapper.ClockInMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ClockInMapper clockInMapper;

    @Resource
    private CallInOperate callInOperate;

    @Resource
    private KnockOffOperate knockOffOperate;

    @GetMapping("/1")
    public List<ClockIn> test (){
        String dept = "";
        String userName = "张家杰";
        String title = "";
        String startTime = "";
        String endTime = "";
        String type = "";
        QueryWrapper<ClockIn> clockInQueryWrapper = new QueryWrapper<>();
        clockInQueryWrapper.like(StringUtils.isNotBlank(dept),"dept",dept);
        clockInQueryWrapper.like(StringUtils.isNotBlank(userName),"user_name",userName);
        clockInQueryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        clockInQueryWrapper.ge(StringUtils.isNotBlank(startTime),"clock_date",startTime);
        clockInQueryWrapper.le(StringUtils.isNotBlank(endTime),"clock_date",endTime);
        clockInQueryWrapper.eq(StringUtils.isNotBlank(type),"type",type);
        clockInQueryWrapper.orderByDesc("clock_in_time");
        Page<ClockIn> clockInPage = new Page<>(1, 10);
        Page<ClockIn> inPage = clockInMapper.selectByCallInCondition(clockInPage, clockInQueryWrapper);
        System.out.println(inPage.getRecords());
        System.out.println(inPage.getTotal());
        return inPage.getRecords();
    }

    @GetMapping("/2")
    public void test2(){
        boolean overTime = DateUtils.isOverTime();
        if (overTime){
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

}
