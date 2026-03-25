package com.stu.helloserver.exception;

import com.stu.helloserver.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 打印异常信息到控制台
        e.printStackTrace();
        // 返回统一格式的错误信息
        return Result.error(500, "系统异常: " + e.getMessage());
    }
}
