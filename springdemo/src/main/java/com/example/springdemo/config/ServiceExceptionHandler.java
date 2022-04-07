package com.example.springdemo.config;

import com.example.springdemo.common.exception.ServiceException;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(-1)
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public Response exceptionHandler(ServiceException e) {
        log.error(e.getMessage());
        return ResultWrap.error(e); // 可以根据具体场景调整返回数据结构体
    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        log.error("整体异常情况{}",e.toString());
    }
}