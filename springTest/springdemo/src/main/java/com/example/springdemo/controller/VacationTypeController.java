package com.example.springdemo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.springdemo.common.model.Response;
import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.service.VacationTypeService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Year;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-08 15:06
 * @Describe
 */


@RestController
@RequestMapping("/api/vacation")
public class VacationTypeController {

    private  String chidren = "2019-06-04";
    private  Integer days   = 5;

    @Autowired
    VacationTypeService vacationTypeService;

    @GetMapping("/parenting")
    public String parenting() throws ParseException {
        // 计算当前年度的 育儿假
        // ( 当前时间 - 出生日期 + 1 ) /365 * 假期天数

        // 当前年
        int currYear = Year.now().getValue();
        // 子女的出生年
        Integer birthYear = Integer.parseInt(chidren.substring(0,4));

        if(currYear - birthYear > 3){
            return  null;
        }else if(currYear == birthYear){
            System.out.println("今年生的");
        }else{
            System.out.println("往年生的");
            chidren = currYear -1  + chidren.substring(4);
        }
        Double d = calculate(chidren);
        System.out.println(d);

        return String.valueOf(d);
    }

    private double calculate(String chidren){
        if(StringUtils.isEmpty(chidren)){
            return 0;
        }
        DateTime end = new DateTime();
        DateTime start = new DateTime(chidren);
        Period p = new Period(start,end, PeriodType.days());
        int day = p.getDays();
        if(day > 365 ){
            day = day - 365;
        }
        Double f = (double)day / 365 * 5;
        return f;
    }

    private String calculateStr(String chidren){
        DecimalFormat df = new DecimalFormat("0.0000"); //设置保留位数
        if(StringUtils.isEmpty(chidren)){
            return "";
        }
        DateTime end = new DateTime();
        DateTime start = new DateTime(chidren);
        Period p = new Period(start,end, PeriodType.days());
        int day = p.getDays();
        return df.format(( (float)day / 365  ) * 5);
    }

    @GetMapping("/ill")
    Response ill(){
        System.out.println(vacationTypeService.illVacation());
        return ResultWrap.ok("病假");
    }
}
