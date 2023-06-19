package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "customer_need_type")
public class CustomerNeedType {
  @TableId
  private String customerNeedId;
  private String customerNeedName;
  private int customerNeedType;
}
