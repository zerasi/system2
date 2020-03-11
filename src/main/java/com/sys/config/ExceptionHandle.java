package com.sys.config;

import com.sys.utils.ResponseCode;
import com.sys.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Results handle(Exception e) {
        log.info("进入error");

        if(e instanceof AccessDeniedException){
            log.error("权限异常 {}", e);
            return Results.failure(ResponseCode.NO_PERMISSION_CODE);
        }
        log.error("系统异常 {}", e);

        return Results.failure();
    }
}