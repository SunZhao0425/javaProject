package com.example.springdemo.service;

import com.example.springdemo.entity.PsUser;
import com.example.springdemo.entity.UserEntity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-26 15:27
 * @Describe
 */


public interface PsUserService {
    UserEntity getUser(Integer id);

//    int insertUser(PsUser psUser);
//
//    int updateUser(PsUser psUser);
//
//    List<UserEntity> getUserList(Integer page, Integer pageSize);
}
