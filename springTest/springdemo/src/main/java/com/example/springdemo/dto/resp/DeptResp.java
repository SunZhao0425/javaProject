package com.example.springdemo.dto.resp;

import lombok.Data;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 14:26
 * @Describe
 */

@Data
public class DeptResp {
    private Long deptId;
    private Long pid;
    private Integer subCount;
    private String name;
    private Integer deptSort;
    private Integer enabled;
}