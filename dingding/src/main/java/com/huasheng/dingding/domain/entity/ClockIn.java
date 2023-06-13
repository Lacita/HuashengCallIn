package com.huasheng.dingding.domain.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@ApiModel(value = "打卡记录")
@TableName(value = "clock_in")
public class ClockIn implements Serializable {
  @TableId(type = IdType.ID_WORKER)
  @ApiModelProperty(value = "打卡记录ID")
  private Long id;
  @ApiModelProperty(value = "打卡用户")
  private String userName;
//  @JsonFormat(pattern = "yyyy-MM-dd")
//  @JSONField(format = "yyyy/MM/dd")
  @ApiModelProperty(value = "打卡日期")
  private Date clockDate;
  @ApiModelProperty(value = "部门")
  private String dept;
  @ApiModelProperty(value = "职位")
  private String title;
  @ApiModelProperty(value = "工号")
  private String jobNumber;
  @ApiModelProperty(value = "打卡位置")
  private String location;
//  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "打卡时间")
  private String clockInTime;
  @ApiModelProperty(value = "打卡类型")
  private String type;
  @ApiModelProperty(value = "打卡项目")
  private String project;
  @ApiModelProperty(value = "打卡备注")
  private String note;
  @TableField(exist = false)
  private String callInType;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @ApiModelProperty(value = "加班打卡时间")
  private String overTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @ApiModelProperty(value = "加班结束打卡时间")
  private String overTimeEnd;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @ApiModelProperty(value = "下班时间")
  private String knockOffTime;
  @ApiModelProperty(value = "外勤上班打卡位置")
  private String fieldLocation;
  @ApiModelProperty(value = "外勤下班打卡位置")
  private String fieldKnockLocation;
  @ApiModelProperty(value = "工作时长")
  private String workTimeResult;
  @ApiModelProperty(value = "加班时长")
  private String overTimeResult;
  @ApiModelProperty(value = "迟到情况")
  private String lateSituation;
  @ApiModelProperty(value = "早退情况")
  private String earlySituation;

}
