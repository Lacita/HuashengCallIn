package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户信息DTO")
@TableName(value = "customer_info")
public class CustomerInfoMessage {
  @ApiModelProperty(value = "主键",required = true)
  @TableId(type = IdType.ID_WORKER)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  private String customerCode;
  private String customerName;
  private String customerType;
  private String currentStatus;
  private String customerAddress;
  private String customerPhone;
  private String productionCapacity;
  private String deviceName;
  private int customerNeed;
  private String businessCompetitor;
  private String isMarket;
  private String operateUser;


  /***
   *   @TableId(type = IdType.ID_WORKER)
   *   @JsonSerialize(using = ToStringSerializer.class)
   *   @ApiModelProperty(value = "用户ID")
   *   private Long id;
   *   @ApiModelProperty(value = "客户代码")
   *   private String customerCode;
   *   @ApiModelProperty(value = "客户名称")
   *   private String customerName;
   *   @ApiModelProperty(value = "客户类型")
   *   private String customerType;
   *   @ApiModelProperty(value = "当前阶段")
   *   private String currentStatus;
   *   @ApiModelProperty(value = "用户ID")
   *   private String customerAddress;
   *   @ApiModelProperty(value = "客户手机")
   *   private String customerPhone;
   *   @ApiModelProperty(value = "企业类型")
   *   private String industryType;
   *   @ApiModelProperty(value = "企业规模")
   *   private String industrySize;
   *   @ApiModelProperty(value = "公司性质")
   *   private String corporateNature;
   *   @ApiModelProperty(value = "公司规模")
   *   private String companySize;
   *   @ApiModelProperty(value = "营收规模")
   *   private String revenueScale;
   *   @ApiModelProperty(value = "回款能力")
   *   private String returnRate;
   *   @ApiModelProperty(value = "是否上市")
   *   private String isMarket;
   *   @ApiModelProperty(value = "股票代码")
   *   private String stockCode;
   *   @ApiModelProperty(value = "产品类型")
   *   private String productType;
   *   @ApiModelProperty(value = "设备名称")
   *   private String deviceName;
   *   @ApiModelProperty(value = "操作用户")
   *   private String operateUser;
   */

}
