package com.huasheng.dingding.domain.dto;

import lombok.Data;

@Data
public class CustomerInfoDto {
    private long id;
    private String customerCode;
    private String customerName;
    private int customerType;
    private String currentStatus;
    private String customerAddress;
    private String customerPhone;
    private String industryType;
    private String industrySize;
    private String corporateNature;
    private String companySize;
    private String revenueScale;
    private String returnRate;
    private int isMarket;
    private String stockCode;
    private String productType;
    private String deviceName;
    private String operateUser;
    private String customerId;
    private long page;
    private long size;
}
