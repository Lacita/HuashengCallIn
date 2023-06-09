package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "customer_info")
public class CustomerInfo implements Serializable {
  @TableId(type = IdType.ID_WORKER)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  private String customerCode;
  private String customerName;
  private int customerType;
  private int currentStatus;
  private String customerAddress;
  private String customerPhone;
  private String industryType;
  private String industrySize;
  private String corporateNature;
  private String companySize;
  private String revenueScale;
  private String returnRate;
  private int isMarket;
  private String stockCode;
  private String productType;
  private String deviceName;
  private String operateUser;

}
