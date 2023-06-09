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
    @ExcelProperty({"客服反馈记录","拜访结果"})
    private String visitSituation;
    @ExcelProperty({"客服反馈记录","价格洽谈结果"})
    private String priceSituation;
    @ExcelProperty({"客服反馈记录","回款情况"})
    private String payForSituation;
    @ExcelProperty({"客服反馈记录","存在问题"})
    private String existingProblem;
    @ExcelProperty({"客服反馈记录","解决方案"})
    private String solution;
    @ExcelProperty({"客服反馈记录","反馈客服"})
    private String feedbackUser;
    @ExcelProperty({"客服反馈记录","客服反馈时间"})
    private String customerUpdateTime;
    @ExcelProperty({"研发反馈","打样情况"})
    private String researchSituation;
    @ExcelProperty({"研发反馈","存在问题"})
    private String researchExistingProblem;
    @ExcelProperty({"研发反馈","解决方案"})
    private String researchSolution;
    @ExcelProperty({"研发反馈","研发反馈人员"})
    private String researchFeedbackUser;
    @ExcelProperty({"研发反馈","研发反馈时间"})
    private String researchUpdateTime;

}
