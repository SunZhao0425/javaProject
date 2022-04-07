package com.example.springdemo.channel;

import com.alibaba.fastjson.JSONObject;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-29 10:20
 * @Describe
 */

@RetrofitClient(baseUrl = "https://api.weixin.qq.com", connectTimeoutMs = 20000, readTimeoutMs = 20000)
public interface WeChatRetrofitClient {

    /**
     *
     * @param appid
     * @param secret
     * @param grant_type
     * @return
     */
    @GET("/sns/jscode2session")
    JSONObject queryCode2Session(@Query("appid") String appid,@Query("secret") String secret,
                                 @Query("grant_type") String grant_type,@Query("js_code") String js_code);


    /**
     *
     * @param appid
     * @param secret
     * @param grant_type
     * @return
     */
    @GET("/cgi-bin/token")
    JSONObject queryGetAccessToken(@Query("appid") String appid, @Query("secret") String secret, @Query("grant_type") String grant_type);

}