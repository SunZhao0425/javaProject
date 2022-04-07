package com.example.springdemo.common.pattern.singleton;

import static org.apache.tomcat.jni.Global.lock;

/**
 * @version V1.0
 * @ClassName 双重校验锁单例模式
 * @Author zhaohongyan
 * @Date: 2022-01-12 14:48
 * @Describe
 */


public class VolatileSingletonPattern {

    /**
     * 私有实例，volatile修饰的变量是具有可见性的（即被一个线程修改后，其他线程立即可见）
     */
    private static  VolatileSingletonPattern instance;

    /**
     * 私有构造方法
     */
    private VolatileSingletonPattern() { }

    public static VolatileSingletonPattern getInstance(){
        // 先判断是否存在,不存在再加锁处理
        if(instance == null){
            // 在同一个时刻加了锁的那部分程序只有一个线程可以进入
            synchronized (VolatileSingletonPattern.class){
                if(instance == null){
                    instance = new VolatileSingletonPattern();
                }
            }
        }
        return  instance;
    }

}
