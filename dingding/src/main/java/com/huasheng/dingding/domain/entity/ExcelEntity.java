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
    @ExcelProperty("打卡时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private Date clockInTime;
    @ExcelProperty("打卡类型")
    private String type;
    @ExcelProperty("打卡项目")
    private String project;
    @ExcelProperty("备注")
    private String note;


}
