package com.example.springdemo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-02-25 10:23
 * @Describe
 *
 * @Target({ElementType.TYPE,ElementType.METHOD})
 * 说明了Annotation所修饰的对象范围：
 * Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
 * 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 * 取值(ElementType)有：
 * 　1.CONSTRUCTOR:用于描述构造器
 * 　2.FIELD:用于描述域
 * 　3.LOCAL_VARIABLE:用于描述局部变量
 * 　4.METHOD:用于描述方法
 * 　5.PACKAGE:用于描述包
 * 　6.PARAMETER:用于描述参数
 * 　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * @Retention(RetentionPolicy.RUNTIME)
 * 定义annotation被保留的时间长短
 * 作用: 表示需要在什么级别保存该注释信息,用于描述注解的生命周期
 * 取值（RetentionPoicy）有：
 * 　1.SOURCE:在源文件中有效（即源文件保留）
 * 　2.CLASS:在class文件中有效（即class保留）
 * 　3.RUNTIME:在运行时有效（即运行时保留）
 * @Documented
 * 注解表明这个注解应该被 javadoc工具记录.
 * 默认情况下,javadoc是不包括注解的. 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理,
 * 所以注解类型信息也会被包括在生成的文档中，是一个标记注解，没有成员。
 * @Component
 * 作用: 实现bean 的注入
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UrlAccess {
    String url();
}
