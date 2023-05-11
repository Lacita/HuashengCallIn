package com.huasheng.dingding.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "打卡项目信息条件查询")
public class ClockQueryDto {
    @ApiModelProperty(value = "打卡开始时间")
    private String startTime;
    @ApiModelProperty(value = "打卡结束时间")
    private String endTime;
    @ApiModelProperty(value = "打卡用户")
    private String userName;
    @ApiModelProperty(value = "打卡部门")
    private String dept;
    @ApiModelProperty(value = "打卡类型")
    private String type;
    @ApiModelProperty(value = "打卡员工岗位")
    private String title;
    @ApiModelProperty(value = "当前页码")
    @NotNull(message = "页码不能为空")
    private int startPage;
    private int size;
}
