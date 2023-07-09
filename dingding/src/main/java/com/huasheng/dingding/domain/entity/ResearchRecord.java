package com.huasheng.dingding.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;


@Data
@TableName(value = "research_record")
public class ResearchRecord {
  @TableId(type = IdType.ID_WORKER)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  private long customerId;
  private String researchSituation;
  private String existingProblem;
  private String solution;
  private String feedbackUser;
  private String updateTime;
  private String samplingTime;
  private String samplingInfo;
  private String coatingScheme;
}
