package com.huasheng.dingding.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "客户信息VO")
@TableName(value = "customer_info")
@Data
public class CustomerInfoVo {
    @ApiModelProperty(value = "主键",required = true)
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String customerCode;
    private String customerName;
    private String customerType;
    private String currentStatus;
    private String salePerYear;
    private String customerAddress;
    private String customerPhone;
    private String productionCapacity;
    private String deviceName;
    private List<String> customerNeed;
    private List<String> customerId;
    private String customerNeedDevice;
    private String businessCompetitor;
    private String isMarket;
    private String operateUser;
}
