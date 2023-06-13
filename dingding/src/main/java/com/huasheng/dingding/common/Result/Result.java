package com.huasheng.dingding.common.Result;

import com.huasheng.dingding.common.Constant.CodeEnum;
import io.swagger.annotations.ApiModelProperty;

//@Data
public class Result<T> {
    @ApiModelProperty(value = "响应码结果，2000正常，3000为返回有异常",notes = "响应码",example = "2000")
    private int code;
    @ApiModelProperty(value = "",notes = "返回结果",example = "处理成功")
    private String message;
    @ApiModelProperty(notes = "响应消息",example = "消息成功处理！")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(CodeEnum handleSuccess) {
    }

    public Result(CodeEnum handleSuccess, T data) {

    }
}
