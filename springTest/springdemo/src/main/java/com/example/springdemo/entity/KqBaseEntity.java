package com.example.springdemo.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 16:16
 * @Describe
 */


@Data
public class KqBaseEntity {
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
}
