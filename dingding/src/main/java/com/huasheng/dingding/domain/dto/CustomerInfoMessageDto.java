package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户信息DTO")
public class CustomerInfoMessageDto {
  @ApiModelProperty(value = "主键",required = true)
  private long id;
  private String customerCode;
  private String customerName;
  private String customerType;
  private String currentStatus;
  private String customerAddress;
  private String customerPhone;
  private String production_Capacity;
  private String deviceName;
  private Integer customerOrigin;
  private int customerNeed;
  private String business_Competitor;
  private String isMarket;
  private String operateUser;
  @ApiModelProperty(value = "页码")
  private long page;
  @ApiModelProperty(value = "展示数量")
  private long size;


  /***
   *     @ApiModelProperty(value = "主键",required = true)
   *     private Long id;
   *     @ApiModelProperty(value = "客户代码")
   *     private String customerCode;
   *     @ApiModelProperty(value = "客户名称",required = true)
   *     private String customerName;
   *     @ApiModelProperty(value = "客户类型")
   *     private String customerType;
   *     @ApiModelProperty(value = "当前交易状态")
   *     private String currentStatus;
   *     @ApiModelProperty(value = "客户地址")
   *     private String customerAddress;
   *     @ApiModelProperty(value = "客户手机")
   *     private String customerPhone;
   *     @ApiModelProperty(value = "企业类型")
   *     private String industryType;
   *     @ApiModelProperty(value = "企业规模")
   *     private String industrySize;
   *     @ApiModelProperty(value = "企业性质")
   *     private String corporateNature;
   *     @ApiModelProperty(value = "公司规模")
   *     private String companySize;
   *     @ApiModelProperty(value = "营收规模")
   *     private String revenueScale;
   *     @ApiModelProperty(value = "回款能力")
   *     private String returnRate;
   *     @ApiModelProperty(value = "是否上市")
   *     private String isMarket;
   *     @ApiModelProperty(value = "股票代码")
   *     private String stockCode;
   *     @ApiModelProperty(value = "产品类型")
   *     private String productType;
   *     @ApiModelProperty(value = "设备名称")
   *     private String deviceName;
   *     @ApiModelProperty(value = "操作用户")
   *     private String operateUser;
   *     @ApiModelProperty(value = "客户ID")
   *     private String customerId;
   *     @ApiModelProperty(value = "页码")
   *     private long page;
   *     @ApiModelProperty(value = "展示数量")
   *     private long size;
   */

}
