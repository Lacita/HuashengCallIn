package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "客户进展记录DTO")
@Data
public class CustomerRecordDto {
    @ApiModelProperty(value = "客户ID")
    private long customerId;
    @ApiModelProperty(value = "拜访情况")
    private String visitSituation;
    @ApiModelProperty(value = "价格洽谈情况")
    private String priceSituation;
    @ApiModelProperty(value = "付款条件情况")
    private String payForSituation;
    @ApiModelProperty(value = "存在问题")
    private String existingProblem;
    @ApiModelProperty(value = "解决方案")
    private String solution;
    @ApiModelProperty(value = "反馈用户")
    private String feedbackUser;
    @ApiModelProperty(value = "页码")
    private long page;
    @ApiModelProperty(value = "页码尺寸")
    private long size;
}
