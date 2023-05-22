package com.huasheng.dingding.common.Constant;

public enum CodeEnum {

    HANDLE_SUCCESS(2000,"处理成功"),
    HANDLE_FAIL(3000,"处理失败"),
    Frequently_FAIL(3001,"操作频繁"),
    INTERNET_FAIL(3002,"网络异常"),
    NO_ACCESS(4000,"没有访问权限"),
    INTERNAL_ERROR(5000,"服务内部错误");


    public int code;
    public String message;

    CodeEnum(int code,String message){
        this.code=code;
        this.message=message;
    }


}
