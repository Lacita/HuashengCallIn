package com.huasheng.dingding.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ExcelIgnoreUnannotated
public class CustomerExcel {

    @ExcelProperty("客户名称")
    @ColumnWidth(20)
    private String customerName;
    @ExcelProperty({"销售进度","拜访情况"})
    private String visitSituation;
    @ExcelProperty({"销售进度","存在问题"})
    private String existingProblem;
    @ExcelProperty({"销售进度","解决方案"})
    private String solution;
    @ExcelProperty({"销售进度","未来计划"})
    private String plan;
    @ExcelProperty({"销售进度","反馈客服"})
    private String feedbackUser;
    @ExcelProperty({"销售进度","客服反馈时间"})
    private String customerUpdateTime;
    @ExcelProperty({"研发进度","打样时间"})
    private String samplingTime;
    @ExcelProperty({"研发进度","样品信息"})
    private String samplingInfo;
    @ExcelProperty({"研发进度","工况信息"})
    private String researchSituation;
    @ExcelProperty({"研发进度","涂层方案"})
    private String coatingScheme;
    @ExcelProperty({"研发进度","测试结果"})
    private String researchSolution;
    @ExcelProperty({"研发进度","工艺经理"})
    private String researchFeedbackUser;
    @ExcelProperty({"研发进度","研发反馈时间"})
    private String researchUpdateTime;

}
