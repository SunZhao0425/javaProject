package com.example.springdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springdemo.dto.req.UserQueryReq;
import com.example.springdemo.entity.UserEntity;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 15:11
 * @Describe
 */


public interface UserService  {

    IPage<UserEntity>  queryAllUserList(UserQueryReq userQueryReq);

    int insertUser(UserEntity userEntity);

    int updateUser(UserEntity userEntity);

    UserEntity queryUserOne(String id);

    int deleteUser(String id);

}
