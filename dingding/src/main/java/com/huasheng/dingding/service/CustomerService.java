package com.huasheng.dingding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.domain.dto.CustomerInfoDto;
import com.huasheng.dingding.domain.dto.CustomerRecordDto;
import com.huasheng.dingding.domain.dto.ResearchRecordDto;
import com.huasheng.dingding.domain.entity.CustomerInfo;
import com.huasheng.dingding.domain.vo.CustomerInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface CustomerService extends IService<CustomerInfo> {
    Result<CustomerInfoVo> showCustomerInfo(CustomerInfoDto customerInfoDto);

    Result<String> updateCustomerInfo(CustomerInfoDto customerInfoDto);

    Result<String> insertCustomerInfo(CustomerInfoDto customerInfoDto);

    Result<Map<String,Object>> selectCustomerInfoRecord(CustomerInfoDto customerInfoDto);

    Result<Map<String,Object>> selectResearchInfoRecord(CustomerInfoDto customerInfoDto);

    Result<String> insertCustomerProcess(CustomerRecordDto customerRecordDto);

    Result<String> insertResearchProcess(ResearchRecordDto researchRecordDto);

    Result<List<CustomerInfo>> getCustomerLists();

    Result<Map<String, Object>> showCustomerVo(CustomerInfoDto customerInfoDto);

    void exportCustomerRecord(CustomerInfoDto customerInfoDto, HttpServletResponse response);

    Result<Map<String, Object>> showCustomerReportVo(CustomerInfoDto customerInfoDto);
}
