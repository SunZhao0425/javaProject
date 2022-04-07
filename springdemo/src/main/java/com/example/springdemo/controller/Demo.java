package com.example.springdemo.controller;

import com.example.springdemo.common.exception.ServiceException;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.entity.PsUser;
import com.example.springdemo.entity.UserEntity;
import com.example.springdemo.service.PsUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;


/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-25 18:41
 * @Describe
 */

@Slf4j
@RestController
@RequestMapping("/login")
public class Demo {
    @Autowired
    PsUserService psUserService;


    @SneakyThrows
    @GetMapping("/test")
    public List<UserEntity> index(@RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {

        UserEntity userEntity  = psUserService.getUser(1);
        System.out.println(userEntity.getNickName());
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userEntity);
//        List<UserEntity> userEntities = psUserService.getUserList(page,pageSize);

        return userEntities;
    }


    @GetMapping("/time")
    Response time(){
        if(true){
            throw new ServiceException(1,1,"啦啦啦1121");
        }
        return ResultWrap.ok();
    }
}
