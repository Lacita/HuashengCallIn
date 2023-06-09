package com.huasheng.dingding.domain.dto;

import lombok.Data;

@Data
public class CustomerRecordDto {
    private long customerId;
    private String visitSituation;
    private String priceSituation;
    private String payForSituation;
    private String existingProblem;
    private String solution;
    private String feedbackUser;
    private long page;
    private long size;
}
