package com.stu.helloserver.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("Global error handler caught an exception:", e);
        
        String errorMsg = e.getMessage();
        if (errorMsg == null && e.getCause() != null) {
            errorMsg = e.getCause().getMessage();
        }
        if (errorMsg == null) {
            errorMsg = e.getClass().getSimpleName();
        }
        
        return Result.error(ResultCode.ERROR.getCode(), errorMsg);
    }
}
