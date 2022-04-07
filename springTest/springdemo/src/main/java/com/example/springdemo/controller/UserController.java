package com.example.springdemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.dto.req.UserQueryReq;
import com.example.springdemo.entity.DeptEntity;
import com.example.springdemo.entity.UserEntity;
import com.example.springdemo.mapper.DeptMapper;
import com.example.springdemo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 11:48
 * @Describe
 */

@Api(tags = "系统: 用户管理")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DeptMapper deptMapper;

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public Response query(@RequestBody  @Validated UserQueryReq userQueryReq) {
        System.out.println(userQueryReq);
        IPage<UserEntity> userEntityIPage = userService.queryAllUserList(userQueryReq);
        return ResultWrap.ok(userEntityIPage);
    }

    @ApiOperation("新增用户")
    @PostMapping("/create")
    public Response create() {
        UserQueryReq userQueryReq = new UserQueryReq();
        UserEntity userEntity = new UserEntity();
        Integer userCount = userService.insertUser(userEntity);
        if(userCount <= 0 ){
            return ResultWrap.error(200,-1,"新增新用户失败");
        }
        return ResultWrap.ok();
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public Response update() {
        UserQueryReq userQueryReq = new UserQueryReq();
        IPage<UserEntity> userEntityIPage = userService.queryAllUserList(userQueryReq);
        return ResultWrap.ok(userEntityIPage);
    }


    @ApiOperation("部门列表")
    @PostMapping("/li")
    public Response list() {
        DeptEntity deptEntity =  deptMapper.selectDeptById(1000239);
        return ResultWrap.ok(deptEntity);
    }
}
