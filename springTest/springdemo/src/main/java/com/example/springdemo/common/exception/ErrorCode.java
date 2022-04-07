package com.example.springdemo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-09 17:09
 * @Describe
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNAUTHORIZED(401, 10002, "权限不足"),
    FORBIDDEN(403,10003,"禁止访问"),
    PARAM_ERROR(400, 10004, "参数错误");

    private int status;
    private int code;
    private String message;
}
