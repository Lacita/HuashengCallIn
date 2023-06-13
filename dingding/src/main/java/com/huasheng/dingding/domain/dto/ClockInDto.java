package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "前端查询打卡记录DTO")
public class ClockInDto implements Serializable {

    @ApiModelProperty(value = "员工姓名",required = true)
    private String userName;
    @ApiModelProperty(value = "打卡类型",required = true)
    private String type;
    @ApiModelProperty(value = "打卡地点",required = true)
    private String location;
    @ApiModelProperty(value = "打卡项目",required = true)
    private String project;
    @ApiModelProperty(value = "打卡备注")
    private String note;
    @ApiModelProperty(value = "员工ID",required = true)
    private String userId;
    @ApiModelProperty(value = "员工编号",required = true)
    private String jobNumber;
    @ApiModelProperty(value = "获取登录token")
    private String code;
    @ApiModelProperty(value = "获取经度",required = true)
    private double longitude;
    @ApiModelProperty(value = "获取纬度",required = true)
    private double latitude;
}
