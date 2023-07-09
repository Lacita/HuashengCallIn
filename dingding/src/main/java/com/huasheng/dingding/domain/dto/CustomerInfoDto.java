package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "用户信息DTO")
@Data
public class CustomerInfoDto {
    @ApiModelProperty(value = "主键",required = true)
    private long id;
    private String customerCode;
    private String customerName;
    private String customerType;
    private Integer customerOrigin;
    private String currentStatus;
    private String salePerYear;
    private String customerAddress;
    private String customerPhone;
    private String productionCapacity;
    private String deviceName;
    private List<String> customerNeed;
    private String customerNeedDevice;
    private String businessCompetitor;
    private String isMarket;
    private String operateUser;
    private String customerId;
    @ApiModelProperty(value = "页码")
    private long page;
    @ApiModelProperty(value = "展示数量")
    private long size;
}
