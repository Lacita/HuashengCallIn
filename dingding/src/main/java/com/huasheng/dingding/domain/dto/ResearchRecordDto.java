package com.huasheng.dingding.domain.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ResearchRecordDto {
    private long customerId;
    private String researchSituation;
    private String existingProblem;
    private String solution;
    private String feedbackUser;
    private long page;
    private long size;
}
