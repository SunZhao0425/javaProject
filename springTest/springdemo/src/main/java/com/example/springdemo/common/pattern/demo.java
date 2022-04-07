package com.example.springdemo.common.pattern;


import com.example.springdemo.common.pattern.adapter.ClassAdapter;
import com.example.springdemo.common.pattern.observe.BinaryObservePattern;
import com.example.springdemo.common.pattern.observe.HexaObservePattern;
import com.example.springdemo.common.pattern.observe.OctalObservePattern;
import com.example.springdemo.common.pattern.observe.SubjectPattern;

import java.util.ArrayList;
import java.util.List;


/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-02 11:07
 * @Describe
 */


public class demo {

    public static void main(String[] args) {
//        SubjectPattern subjectP = new SubjectPattern();
//
//        BinaryObservePattern binaryObservePattern = new BinaryObservePattern(subjectP);
//        HexaObservePattern hexaObservePattern = new HexaObservePattern(subjectP);
//        OctalObservePattern octalObservePattern = new OctalObservePattern(subjectP);
//
//        System.out.println("第一次状态改变: 16");
//        subjectP.setState(500);
//        System.out.println("第二次状态改变: 10");
//        subjectP.setState(20);

        ClassAdapter classAdapter = new ClassAdapter();
        classAdapter.request();

        List<String> doMainNames = new ArrayList<>();
        doMainNames.add("14");
        doMainNames.add("25");
        doMainNames.add("35");
        doMainNames.add("54");
        doMainNames.add("52");
        doMainNames.add("26");
        doMainNames.add("72");
        doMainNames.add("84");
        doMainNames.add("95");
        doMainNames.add("105");

        doMainNames.forEach(doMainName->{
            System.out.println(doMainName);
        });
    }
}
