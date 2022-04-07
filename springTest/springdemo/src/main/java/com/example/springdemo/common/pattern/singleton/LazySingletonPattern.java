package com.example.springdemo.common.pattern.singleton;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-11 19:21
 * @Describe
 */


import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 懒(lazy)汉式
 * 优点:
 *  1.
 */
public  class LazySingletonPattern{
    private LazySingletonPattern(){}

    private static LazySingletonPattern instance = null;
    public static LazySingletonPattern getInstance(){
        if(instance == null){
            // 多个线程判断instance都为null时,在执行new操作时多线程会出现重复情况
            instance = new LazySingletonPattern();

        }
        return instance;
    }

    public void  getMessage(){
        System.out.println("啦啦啦啦啦");
    }
}