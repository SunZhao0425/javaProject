package com.example.springdemo.controller;

import com.example.springdemo.common.exception.ServiceException;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.entity.PsUser;
import com.example.springdemo.entity.UserEntity;
import com.example.springdemo.service.PsUserService;
import com.example.springdemo.service.VacationTypeService;
import com.example.springdemo.utils.DbUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                  HttpServletResponse response)
    {
//        UserEntity userEntity  = psUserService.getUser(1);
//        System.out.println(userEntity.getNickName());
        List<UserEntity> userEntities = new ArrayList<>();
//
//        userEntities.add(userEntity);
//        List<UserEntity> userEntities = psUserService.getUserList(page,pageSize);

        DbUtils.querySumbitOffer(response);

        return userEntities;
    }


    @GetMapping("/time")
    Response time(){
        if(true){
            throw new ServiceException(1,1,"啦啦啦1121");
        }
        return ResultWrap.ok();
    }

    @Autowired
    VacationTypeService vacationTypeService;

    @ResponseBody
    @PostMapping("/importCheckSpeaker")
    public Object importCheckSpeaker(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request, HttpServletResponse response) {

        if (file.isEmpty()) {
            return ResultWrap.error(-1,-1,"文件为空");
        }
        try {
            int count = vacationTypeService.importCheckSpeaker(file);
            if (count > 0) {
                return ResultWrap.ok("导入成功");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResultWrap.ok();
    }

}
