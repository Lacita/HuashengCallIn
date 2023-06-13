package com.huasheng.dingding.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "登录查询")
@Data
@TableName(value = "sys_user")
public class LoginUser implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private String id;
    @ApiModelProperty(value = "登录账户",required = true)
    private String userName;
    @ApiModelProperty(value = "登录密码",required = true)
    private String password;
    @ApiModelProperty(value = "用户状态",required = true)
    private int status;
    @ApiModelProperty(value = "用户备注")
    private String note;
}
