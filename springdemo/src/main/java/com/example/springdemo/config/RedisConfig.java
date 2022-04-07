package com.example.springdemo.config;

import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-29 16:43
 * @Describe
 */


public class RedisConfig {

    /**
     * 连接
     * @return
     */
    public synchronized static Jedis conn(){
        try{
            Jedis jedis = new Jedis("127.0.0.1",6379);
            return  jedis;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 关闭
     * @param jedis
     */
    public static void close(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    public static String setValue( String key, String value,int expire){
        String j = "";
        Jedis jedis = conn();
        if (!Objects.isNull(jedis)){
            if (expire <= 0 ){
                j =  jedis.set(key,value);
            }else {
                j =  jedis.set(key,value);
                jedis.expire(key,expire);
            }
            jedis.close();
        }
        return  j;
    }

    public static String getValue(String key){
        String j = "";
        Jedis jedis = conn();
        if(! Objects.isNull(jedis)){
            j =  jedis.get(key);
            jedis.close();
        }
        return  j;
    }
}
