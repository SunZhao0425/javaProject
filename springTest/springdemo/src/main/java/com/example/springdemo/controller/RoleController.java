package com.example.springdemo.controller;

import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-14 11:55
 * @Describe
 */

@Api(tags = "系统: 角色管理")
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {


    @ApiOperation("角色列表")
    @PostMapping("/list")
    public Response query() {
        return ResultWrap.ok();
    }
}
