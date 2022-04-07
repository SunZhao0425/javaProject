package com.example.springdemo.dto.resp;

import lombok.Data;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-09 18:26
 * @Describe
 */

@Data
public class BaseResp<T> {
    private Boolean success = true;
    private String message = "";
    private Boolean show = true;
    private int code = 200;
    private T data;
}