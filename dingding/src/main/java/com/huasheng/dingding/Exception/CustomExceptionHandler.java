package com.huasheng.dingding.Exception;


import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.mysql.jdbc.exceptions.MySQLDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends Throwable {


    @ExceptionHandler(value = NullPointerException.class)
    public Result<String> errorHandler(NullPointerException e) {
        log.error("出现空指针异常,异常信息为：{}",e.toString());
        return ResultUtils.INTERNET_ERROR_DATA("空指针异常");
    }

    @ExceptionHandler(value = ClassCastException.class)
    public Result<String> errorHandler(ClassCastException e) {
        log.error("数据类型转换错误,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("数据类型转换异常");
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public Result<String> errorHandler(IndexOutOfBoundsException e) {
        log.error("数组越界,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("数组越界");
    }

    @ExceptionHandler(value = SQLDataException.class)
    public Result<String> errorHandler(SQLDataException e) {
        log.error("数组越界,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("数据库数据异常");
    }

    @ExceptionHandler(value = MySQLDataException.class)
    public Result<String> errorHandler(MySQLDataException e) {
        log.error("数组越界,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("数据库数据异常");
    }

    @ExceptionHandler(value = SQLException.class)
    public Result<String> errorHandler(SQLException e) {
        log.error("数组越界,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("数据库异常");
    }

    @ExceptionHandler(value = IOException.class)
    public Result<String> errorHandler(IOException e) {
        log.error("输入输出异常,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("输入输出异常");
    }

    @ExceptionHandler(value = Exception.class)
    public Result<String> errorHandler(Exception e) {
        log.error("未知异常,异常信息为：{}",e.toString());
        return  ResultUtils.ERROR("未知异常");
    }

}
