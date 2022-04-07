package com.example.springdemo.common.pattern.observe;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-14 19:09
 * @Describe
 */


public class HexaObservePattern extends ObservePattern{


    public HexaObservePattern(SubjectPattern subjectPattern){
        this.subjectPattern = subjectPattern;
        this.subjectPattern.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexa String: " + Integer.toBinaryString(subjectPattern.getState()).toUpperCase() +
                Integer.toBinaryString(1));
    }
}
