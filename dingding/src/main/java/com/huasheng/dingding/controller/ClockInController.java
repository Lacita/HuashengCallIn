package com.huasheng.dingding.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.domain.dto.ClockInDto;
import com.huasheng.dingding.domain.dto.ClockQueryDto;
import com.huasheng.dingding.domain.dto.LoginUser;
import com.huasheng.dingding.service.DdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags = "打卡管理")
@ApiSupport(author = "张家杰",order = 1)
@RestController
@RequestMapping("/hs")
@Slf4j
public class ClockInController {

    @Resource
    private DdService ddService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<String> login(@RequestBody LoginUser loginUser){
        if (StringUtils.isBlank(loginUser.getUserName())||StringUtils.isBlank(loginUser.getPassword())) {
            return ResultUtils.ERROR("账号密码不能为空");
        }
        return ddService.login(loginUser.getUserName(), loginUser.getPassword());
    }

    @ApiOperation(value = "打卡接口")
    @RequestMapping(value = "/clockIn",method = RequestMethod.POST)
    public Result<String> clockIn(@RequestBody ClockInDto clockInDto){
        String location = clockInDto.getLocation();
        double longitude = clockInDto.getLongitude();
        double latitude = clockInDto.getLatitude();
        String project = clockInDto.getProject();
        String userName = clockInDto.getUserName();
        String userId = clockInDto.getUserId();
        String type = clockInDto.getType();
        String note = clockInDto.getNote();
        if (StringUtils.isBlank(location)||StringUtils.isBlank(project)||StringUtils.isBlank(userName)||StringUtils.isBlank(type)) {
            return ResultUtils.ERROR("打卡信息不完整");
        }
        return ddService.clockInMessage(userName,userId,type,location,project,note,longitude,latitude);
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public Result<Map<String, Object>> getUserInfo(@RequestBody ClockInDto clockInDto ){
        return ddService.getUserInfo(clockInDto.getCode());
    }

    @ApiOperation(value = "获取打卡记录")
    @RequestMapping(value = "/getClockInRecord",method = RequestMethod.POST)
    public Result<Map<String,Object>> getClockInRecord(@RequestBody ClockQueryDto clockQueryDto){
        return ddService.getClockInRecord(clockQueryDto);
    }

    @ApiOperation(value = "导出记录")
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public void exportCallInRecord (@RequestBody ClockQueryDto clockQueryDto, HttpServletResponse response){
        try {
            ddService.exportCallInRecord(clockQueryDto,response);
        }
        catch (Exception e){
            log.error("导出异常，异常代码为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    @ApiOperation(value = "分页展示打卡项目")
    @RequestMapping(value = "/showProject&projectId={projectId}&projectName={projectName}&page={page}&size={size}",method = RequestMethod.GET)
    public Result<Map<String,Object>> showProject(@PathVariable("projectName") String projectName,
                                                  @PathVariable("projectId") String projectId,
                                                  @PathVariable("page") Integer page,
                                                  @PathVariable("size") Integer size){
        return ddService.showProject(projectId,projectName,page,size);
    }

    @ApiOperation(value = "添加打卡项目")
    @RequestMapping(value = "/addNewProject",method = RequestMethod.POST)
    public Result<String> addNewProject(@RequestParam("projectName") String projectName,
                                        @RequestParam("projectId") String projectId){
        return ddService.addNewProject(projectId,projectName);
    }

    @ApiOperation(value = "更新打卡项目状态")
    @RequestMapping(value = "/updateProject",method = RequestMethod.POST)
    public Result<String> updateProject(@RequestParam("id") Long id){
        return ddService.updateProject(id);
    }

}
