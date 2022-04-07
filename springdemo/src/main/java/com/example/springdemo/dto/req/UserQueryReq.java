package com.example.springdemo.dto.req;

import lombok.Data;
import lombok.NonNull;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 15:41
 * @Describe
 */

@Data
public class UserQueryReq{
    @NonNull
    private Integer page = 1;
    private Integer pageNum = 10;
}
