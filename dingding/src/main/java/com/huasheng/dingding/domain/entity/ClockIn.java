package com.huasheng.dingding.domain.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@TableName(value = "clock_in")
public class ClockIn implements Serializable {
  @TableId(type = IdType.ID_WORKER)
  private Long id;
  private String userName;
//  @JsonFormat(pattern = "yyyy-MM-dd")
//  @JSONField(format = "yyyy/MM/dd")
  private Date clockDate;
  private String dept;
  private String title;
  private String jobNumber;
  private String location;
//  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private String clockInTime;
  private String type;
  private String project;
  private String note;
  @TableField(exist = false)
  private String callInType;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private String overTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private String overTimeEnd;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private String knockOffTime;
  private String fieldLocation;
  private String fieldKnockLocation;
  private String workTimeResult;
  private String overTimeResult;
  private String lateSituation;
  private String earlySituation;

}
