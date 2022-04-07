package com.example.springdemo.common.pattern.singleton;

/**
 * @version V1.0
 * @ClassName 内部单例模式
 * @Author zhaohongyan
 * @Date: 2022-01-12 14:48
 * @Describe
 */

/**
 * 静态内部类
 */
public class InnerSingletonPattern {

    // 在第一次引用类的任何成员是创建实例, 公共语言运行库负责处理变量初初始化
    private static  final  InnerSingletonPattern instance = new InnerSingletonPattern();
    /**
     * 私有构造方法
     */
    private InnerSingletonPattern(){}

    public static InnerSingletonPattern getInstance(){
        return  instance;
    }

}
