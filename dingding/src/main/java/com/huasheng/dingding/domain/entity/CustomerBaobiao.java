package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName(value = "customer_baobiao")
public class CustomerBaobiao {

  @TableId(type = IdType.ID_WORKER)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  private long customerId;
  private String customerName;
  private String currentStatus;
  private String visitSituation;
  private String priceSituation;
  private String payForSituation;
  private String existingProblem;
  private String solution;
  private String feedbackUser;
  private String customerUpdateTime;
  private String researchSituation;
  private String researchExistingProblem;
  private String researchSolution;
  private String researchFeedbackUser;
  private String researchUpdateTime;

}
