package com.example.springdemo.common.pattern.singleton;

/**
 * 单例模式
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-01-11 18:40
 * @Describe
 *
 * 介绍:
 *  单例对象的类必须保证只有一个实例存在
 *
 * 优点:
 *  1. 在内存中只有一个对象,节省内存空间
 *  2. 避免频繁的创建销毁对象, 可以提高性能
 *  3. 避免对共享资源的多重占用, 简化访问
 *  4. 为整个系统提供一个全局访问点
 *
 * 缺点:
 *  1. 不适用于变化频繁的对象
 *  2. 滥用单例将带来一些负面问题,
 *
 * 应用场景举例:
 *  1. Windows的Task Manager（任务管理器）
 *  2. windows的Recycle Bin（回收站）也是典型的单例应用。在整个系统运行过程中，回收站一直维护着仅有的一个实例
 *  3. 应用程序的日志应用
 *  4. Web应用的配置对象的读取
 *  5. 数据库连接池的设计; 因为数据库连接是一种数据库资源。数据库软件系统中使用数据库连接池，主要是节省打开或者关闭数据库连接所引起的效率损耗，这种效率上的损耗还是非常昂贵的，因为何用单例模式来维护，就可以大大降低这种损耗
 *  6. 多线程的线程池的设计
 *  7. 操作系统的文件系统
 *  8. HttpApplication 也是单位例的典型应用
 *
 *
 */


/**
 * 饿汉式单例 : 预先加载法
 * 优点:
 *   1. 线程安全
 *   2. 在类加载的同时已经创建好一个静态对象,调用时反应速度快
 *
 */
public class HungerSingletonPattern {

    // 指向自己实例的私有静态引用,主动创建
    private static HungerSingletonPattern singletonPattern = new HungerSingletonPattern();

    // 私有的构造方法
    private HungerSingletonPattern(){}

    // 以自己实例为返回值的静态的公有方法, 静态工厂方法
    public static HungerSingletonPattern getSingletonPattern(){
        return singletonPattern;
    }
}


