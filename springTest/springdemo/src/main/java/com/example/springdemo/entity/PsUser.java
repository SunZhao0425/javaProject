package com.example.springdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-26 16:34
 * @Describe
 */

@Data
@Accessors(chain = true)
public class PsUser implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private String name;
    private String role;
}
