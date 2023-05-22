package com.huasheng.dingding.common.Result;

import com.huasheng.dingding.common.Constant.CodeEnum;

public class ResultUtils {

    /***
     * 返回接口正确返回参数
     * @param <T>
     * @return
     */
    public static <T> Result<T> SUCCESS(){
        return new Result<T>(CodeEnum.HANDLE_SUCCESS.code,CodeEnum.HANDLE_SUCCESS.message);
    }

    /***
     * 返回接口失败返回参数
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> SUCCESS_DATA(T data){
        return new Result<T>(CodeEnum.HANDLE_SUCCESS.code,CodeEnum.HANDLE_SUCCESS.message,data);
    }


    /***
     * 返回接口正确返回参数并携带data数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ERROR(T data){
        return new Result<T>(CodeEnum.HANDLE_FAIL.code,CodeEnum.HANDLE_FAIL.message,data);
    }

    /***
     * 返回接口失败返回参数并携带data数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ERROR_DATA(T data){
        return new Result<T>(CodeEnum.HANDLE_FAIL.code,CodeEnum.HANDLE_FAIL.message,data);
    }


    public static <T> Result<T> ERRORFrequently_DATA(T data){
        return new Result<T>(CodeEnum.Frequently_FAIL.code,CodeEnum.Frequently_FAIL.message,data);
    }

    /***
     * 返回内部错误参数
     * @param data
     * @param <T>
     * @return
     */

    public static <T> Result<T> INTERNAL_ERROR(T data){
        return new Result<T>(CodeEnum.INTERNAL_ERROR.code,CodeEnum.INTERNAL_ERROR.message);
    }

    /***
     * 返回内部错误参数并携带data数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> INTERNAL_ERROR_DATA(T data){
        return new Result<T>(CodeEnum.INTERNAL_ERROR.code,CodeEnum.INTERNAL_ERROR.message,data);
    }


    public static <T> Result<T> INTERNET_ERROR_DATA(T data){
        return new Result<T>(CodeEnum.INTERNET_FAIL.code,CodeEnum.INTERNET_FAIL.message,data);
    }

    /***
     * 没有访问权限
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> NO_ACCESS_DATA(T data){
        return new Result<T>(CodeEnum.NO_ACCESS.code,CodeEnum.NO_ACCESS.message,data);
    }


}
