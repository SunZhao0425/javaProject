package com.example.springdemo.common.pattern.observe;

/**
 * @version V1.0
 * @ClassName 抽象观察者
 * @Author zhaohongyan
 * @Date: 2022-01-14 19:09
 * @Describe
 */


public  abstract class ObservePattern {
    protected SubjectPattern subjectPattern;
    public abstract void update();
}
