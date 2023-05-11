package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "前端查询打卡记录DTO")
public class ClockInDto implements Serializable {

    @ApiModelProperty(value = "员工姓名")
    private String userName;
    @ApiModelProperty(value = "打卡类型")
    private String type;
    @ApiModelProperty(value = "打卡地点")
    private String location;
    @ApiModelProperty(value = "打卡项目")
    private String project;
    @ApiModelProperty(value = "打卡备注")
    private String note;
    @ApiModelProperty(value = "员工ID")
    private String userId;
    @ApiModelProperty(value = "员工编号")
    private String jobNumber;
    @ApiModelProperty(value = "获取登录token")
    private String code;
    @ApiModelProperty(value = "获取经度")
    private double longitude;
    @ApiModelProperty(value = "获取纬度")
    private double latitude;
}
