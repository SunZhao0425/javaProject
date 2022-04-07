package com.example.springdemo.common.exception;

import lombok.Getter;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-09 17:09
 * @Describe
 */

@Getter
public class BaseException  extends RuntimeException{
    protected  int status;
    protected  int code;

    public BaseException(int status, int code, String message){
        super(message);
        this.status = status;
        this.code = code;
    }

    public BaseException(String message, ErrorCode errorCode){
        super(message);
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

    public BaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

}