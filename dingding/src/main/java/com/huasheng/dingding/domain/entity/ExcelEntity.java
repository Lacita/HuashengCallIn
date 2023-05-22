package com.huasheng.dingding.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ExcelIgnoreUnannotated
public class ExcelEntity {

    @ExcelProperty("员工姓名")
    private String userName;
    @ExcelProperty("打卡日期")
    private String clockDate;
    @ExcelProperty("部门")
    private String dept;
    @ExcelProperty("岗位")
    private String title;
    @ExcelProperty("工号")
    private String jobNumber;
    @ExcelProperty("打卡位置")
    @ColumnWidth(20)
    private String location;
    @ExcelProperty({"打卡时间","上班时间"})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private String clockInTime;
    @ExcelProperty({"打卡时间","下班时间"})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private String knockOffTime;
    @ExcelProperty({"打卡时间","异常考勤","迟到"})
    private String lateSituation;
    @ExcelProperty({"打卡时间","异常考勤","早退"})
    private String earlySituation;
    @ExcelProperty({"打卡时间","上班时长"})
    @ColumnWidth(20)
    private String workTimeResult;
    @ExcelProperty({"加班时间","加班开始时间"})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private String overTime;
    @ExcelProperty({"加班时间","加班结束时间"})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private String overTimeEnd;
    @ExcelProperty({"加班时间","加班时长"})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private String overTimeResult;
    @ExcelProperty("打卡类型")
    private String type;
    @ExcelProperty("打卡项目")
    private String project;
    @ExcelProperty({"外勤地点","外勤上班地点"})
    private String fieldLocation;
    @ExcelProperty({"外勤地点","外勤下班地点"})
    private String fieldKnockLocation;
    @ExcelProperty("备注")
    private String note;


}
