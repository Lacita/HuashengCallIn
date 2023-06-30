package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
  @TableField(strategy = FieldStrategy.IGNORED)
  private String salePerYear;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String customerAddress;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String customerPhone;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String productionCapacity;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String deviceName;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String customerNeed;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String customerNeedDevice;
  @TableField(strategy = FieldStrategy.IGNORED)
  private String businessCompetitor;
  private String isMarket;
  private String operateUser;

}
