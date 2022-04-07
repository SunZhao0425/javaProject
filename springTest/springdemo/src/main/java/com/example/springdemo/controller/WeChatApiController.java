package com.example.springdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.channel.WeChatRetrofitClient;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.config.RedisConfig;
import com.example.springdemo.config.WeChatDefault;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-29 11:43
 * @Describe
 */

@Api(tags = "")
@RestController
@RequestMapping("/api/wx")
@RequiredArgsConstructor
public class WeChatApiController {

    @Autowired
    WeChatRetrofitClient weChatRetrofitClient;

    @Autowired
    WeChatDefault weChatDefault;

    private final String  ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    @ApiOperation("测试")
    @PostMapping("/token")
    public Response querySession(@RequestParam("code") String code) {
        System.out.println("你好");
        JSONObject j = weChatRetrofitClient.queryCode2Session(weChatDefault.getAppid(),weChatDefault.getSecret(),
                weChatDefault.getAuthorizationCode(),code);
        return ResultWrap.ok(j);
    }

    @ApiOperation("测试")
    @PostMapping("/accesstoken")
    public Response queryAccessToken() {
        String token = RedisConfig.getValue(ACCESS_TOKEN_KEY);
        if (StringUtils.isEmpty(token)){
            JSONObject j = weChatRetrofitClient.queryGetAccessToken(weChatDefault.getAppid(),weChatDefault.getSecret(),weChatDefault.getClientCredential());
            token = j.getString("access_token");
            if (! StringUtils.isEmpty(token)) {
                RedisConfig.setValue(ACCESS_TOKEN_KEY,j.getString("access_token"),60);
            }
        }
        return ResultWrap.ok(token);
    }

    /**
     * 微信小程序登陆
     * 1. 配置默认值 appid 和 secret
     * 2. 通过 code 获取
     * 3.
     * @param code
     * @return
     */
    @ApiOperation("微信登陆")
    @GetMapping("/login")
    public Response wxLogin(@RequestParam(value = "code",defaultValue = "") String code){
        if(Objects.isNull(code)){
            return ResultWrap.error(200,-1,"code不能为空");
        }

        String token = "";
        JSONObject j = weChatRetrofitClient.queryGetAccessToken(weChatDefault.getAppid(),weChatDefault.getSecret(),weChatDefault.getClientCredential());

        if(j.containsKey("access_token")){
            token = j.getString("access_token");
        }

        return ResultWrap.ok(token);
    }
}
