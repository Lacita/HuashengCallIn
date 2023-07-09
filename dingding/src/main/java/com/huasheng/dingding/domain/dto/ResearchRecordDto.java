package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@ApiModel(value = "打样记录DTO")
@Data
public class ResearchRecordDto {
    @ApiModelProperty(value = "客户ID",required = true)
    private long customerId;
    @ApiModelProperty(value = "打样情况",required = true)
    private String researchSituation;
    @ApiModelProperty(value = "存在问题",required = true)
    private String existingProblem;
    @ApiModelProperty(value = "解决方案",required = true)
    private String solution;
    @ApiModelProperty(value = "反馈用户",required = true)
    private String feedbackUser;
    private String samplingTime;
    private String samplingInfo;
    private String coatingScheme;
    @ApiModelProperty(value = "页码")
    private long page;
    @ApiModelProperty(value = "页码大小")
    private long size;
}
