package com.huasheng.dingding.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "打卡项目查询")
public class ClockInProjectDto implements Serializable {

    @ApiModelProperty(value = "打卡项目名称")
    private String projectName;
    @ApiModelProperty(value = "打卡项目编号")
    private String projectId;
    @ApiModelProperty(value = "打卡项目状态")
    private Integer projectStatus;
}
