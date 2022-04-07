package com.example.springdemo.common.exception;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-09 17:50
 * @Describe
 */


public class ServiceException extends BaseException{

    public ServiceException(int status, int code, String message) {
        super(status, code, message);
    }

    public ServiceException(String message, ErrorCode errorCode){
        super(message, errorCode);
    }

    public ServiceException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
