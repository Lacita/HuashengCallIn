package com.huasheng.dingding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.domain.dto.CustomerInfoDto;
import com.huasheng.dingding.domain.dto.CustomerRecordDto;
import com.huasheng.dingding.domain.dto.ResearchRecordDto;
import com.huasheng.dingding.domain.entity.CustomerInfo;
import com.huasheng.dingding.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@Api(tags = "商机管理")
@ApiSupport(author = "张家杰",order = 2)
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @ApiOperation(value = "展示客户信息")
    @RequestMapping(value = "/showCustomerInfo",method = RequestMethod.POST)
    public Result<CustomerInfo> showCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.showCustomerInfo(customerInfoDto);
    }

    @ApiOperation(value = "更新客户信息")
    @RequestMapping(value = "/updateCustomerInfo",method = RequestMethod.POST)
    public Result<String> updateCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.updateCustomerInfo(customerInfoDto);
    }

    @ApiOperation(value = "插入客户信息")
    @RequestMapping(value = "/insertCustomerInfo",method = RequestMethod.POST)
    public Result<String> insertCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.insertCustomerInfo(customerInfoDto);
    }

    @ApiOperation(value = "查询客户当前进度")
    @RequestMapping(value = "/selectCustomerInfoRecord",method = RequestMethod.POST)
    public Result<Map<String,Object>> selectCustomerInfoRecord(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.selectCustomerInfoRecord(customerInfoDto);
    }

    @ApiOperation(value = "查询研发当前打样进度")
    @RequestMapping(value = "/selectResearchInfoRecord",method = RequestMethod.POST)
    public Result<Map<String,Object>> selectResearchInfoRecord(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.selectResearchInfoRecord(customerInfoDto);
    }

    @ApiOperation(value = "客服填写当前客户进度")
    @RequestMapping(value = "/insertCustomerProcess",method = RequestMethod.POST)
    public Result<String> insertCustomerProcess(@RequestBody CustomerRecordDto customerRecordDto){
        return customerService.insertCustomerProcess(customerRecordDto);
    }

    @ApiOperation(value = "研发填写当前打样进度")
    @RequestMapping(value = "/insertResearchProcess",method = RequestMethod.POST)
    public Result<String> insertResearchProcess(@RequestBody ResearchRecordDto researchRecordDto){
        return customerService.insertResearchProcess(researchRecordDto);
    }

    @ApiOperation(value = "获取所有项目列表")
    @RequestMapping(value = "/getCustomerLists",method = RequestMethod.GET)
    public Result<List<CustomerInfo>> getCustomerLists(){
        return customerService.getCustomerLists();
    }

    @ApiOperation(value = "条件查询获取用户信息")
    @RequestMapping(value = "/showCustomerVo",method = RequestMethod.POST)
    public Result<Map<String,Object>> showCustomerVo(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.showCustomerVo(customerInfoDto);
    }

    @ApiOperation(value = "客户报表信息查询功能")
    @RequestMapping(value = "/showCustomerReportVo",method = RequestMethod.POST)
    public Result<Map<String,Object>> showCustomerReportVo(@RequestBody CustomerInfoDto customerInfoDto){
        return customerService.showCustomerReportVo(customerInfoDto);
    }

    @ApiOperation(value = "客户信息报表导出功能")
    @RequestMapping(value = "/exportCustomerRecord",method = RequestMethod.POST)
    public void exportCustomerRecord(@RequestBody CustomerInfoDto customerInfoDto, HttpServletResponse response){
         customerService.exportCustomerRecord(customerInfoDto,response);
    }
}
