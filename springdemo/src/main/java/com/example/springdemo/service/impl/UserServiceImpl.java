package com.example.springdemo.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.dto.req.UserQueryReq;
import com.example.springdemo.entity.UserEntity;
import com.example.springdemo.mapper.UserMapper;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 15:12
 * @Describe
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public IPage<UserEntity> queryAllUserList(UserQueryReq userQueryReq) {
        IPage<UserEntity> userEntityIPage = new Page<>(userQueryReq.getPage(), userQueryReq.getPageNum());
//        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
//        IPage<UserEntity> userEntityIPage1 = userMapper.selectPage(userEntityIPage,queryWrapper);
        List<UserEntity> userEntityList = userMapper.getAllUser();
        userEntityIPage.setRecords(userEntityList);
        return userEntityIPage;
    }

    @Override
    public int insertUser(UserEntity userEntity) {
        if(Objects.isNull(userEntity)){
            return -1;
        }
        Integer uCount = userMapper.insertUser(userEntity);
        return uCount;
    }

    @Override
    public int updateUser(UserEntity userEntity) {
        if(Objects.isNull(userEntity)){
            return -1;
        }
        Integer u = userMapper.updateById(userEntity);
        return u;
    }

    @Override
    public UserEntity queryUserOne(String id) {
        return null;
    }

    @Override
    public int deleteUser(String id) {
        return 0;
    }
}
