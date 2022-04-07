package com.example.springdemo.common.pattern.adapter;

/**
 * @version V1.0
 * @ClassName 适配者接口
 * @Author zhaohongyan
 * @Date: 2022-03-03 16:28
 * @Describe
 */


public class Adaptee {

    public void specificRequest(){

        System.out.println("适配者中的业务代码被调用!");
    }
}
