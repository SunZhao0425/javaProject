package com.example.springdemo.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-29 11:19
 * @Describe
 */

@Component
@Data
public class WeChatDefault {

    private String appid = "wxe6d271602d98ee63";

    private String secret = "eb45f8ea33b286691559608e256d22cc";

    private String authorizationCode = "authorization_code";

    private String clientCredential = "client_credential";

}
