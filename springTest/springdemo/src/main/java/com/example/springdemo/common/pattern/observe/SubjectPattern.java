package com.example.springdemo.common.pattern.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @ClassName 目标类或者发布类 Subject
 * @Author zhaohongyan
 * @Date: 2022-01-14 19:09
 * @Describe
 */


public class SubjectPattern {

    // 观察者数组
    private List<ObservePattern> observePatterns = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserves();
    }

    // 新增观察者
    public void attach(ObservePattern observePattern){
        observePatterns.add(observePattern);
    }

    // 通知所有观察者
    private void notifyAllObserves() {
        for (ObservePattern observePattern: observePatterns) {
            observePattern.update();
        }
    }
}
