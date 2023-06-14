package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "客户信息")
@TableName(value = "customer_info")
public class CustomerInfo implements Serializable {
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
  private int customerNeed;
  private String customerNeedDevice;
  private String businessCompetitor;
  private String isMarket;
  private String operateUser;

}
