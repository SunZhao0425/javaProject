package com.example.springdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 14:49
 * @Describe
 */

@Getter
@Setter
public class BaseEntity {
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_time")
    private Timestamp createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;
}
