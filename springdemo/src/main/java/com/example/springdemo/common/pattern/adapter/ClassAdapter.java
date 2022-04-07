package com.example.springdemo.common.pattern.adapter;

/**
 * @version V1.0
 * @ClassName 类适配器
 * @Author zhaohongyan
 * @Date: 2022-03-03 15:09
 * @Describe
 */


public class ClassAdapter  extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
