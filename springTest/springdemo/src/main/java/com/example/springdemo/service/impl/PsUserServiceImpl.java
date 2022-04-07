package com.example.springdemo.service.impl;

import com.example.springdemo.entity.PsUser;
import com.example.springdemo.entity.UserEntity;
import com.example.springdemo.mapper.PsUserMapper;
import com.example.springdemo.service.PsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-26 15:29
 * @Describe
 */
@Slf4j
@Service
public class PsUserServiceImpl implements PsUserService {
    @Resource
    PsUserMapper psUserMapper;

    @Override
    public UserEntity getUser(Integer id) {
        if(Objects.isNull(id)){
            return  null;
        }
        UserEntity userEntity = null;
        try {
            userEntity = psUserMapper.getUser(id);
        }catch (Exception e){
            log.error("获取用户信息错误: {}",e.getMessage());
        }
        return userEntity;
    }

//    @Override
//    public int insertUser(PsUser psUser) {
//        int res;
//        return res = psUserMapper.insert(psUser);
//    }
//
//    @Override
//    public int updateUser(PsUser psUser) {
//        int res;
//        return res = psUserMapper.update(psUser);
//    }
//
//    @Override
//    public List<UserEntity> getUserList(Integer page,Integer pageSize) {
//        List<UserEntity> list = null;
//        try {
//            list = psUserMapper.getUserList(page-1,pageSize);
//        }catch (Exception e){
//            log.error("获取用户信息错误: {}",e.getMessage());
//        }
//        return list;
//    }
}
