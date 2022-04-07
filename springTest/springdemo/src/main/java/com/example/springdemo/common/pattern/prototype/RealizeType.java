package com.example.springdemo.common.pattern.prototype;

import java.util.Objects;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-04 15:03
 * @Describe
 */


public class RealizeType  implements  Cloneable{

    RealizeType(){
        System.out.println("具体原型创建成功");
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
