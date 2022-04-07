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
public class UserResp {
    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String gender;
    private String phone;
    private String email;
    private String avatarName;
    private String avaterPath;
    private Integer isAdmin;
    private DeptResp dept;
}
