package com.example.springdemo.service.impl;

import com.example.springdemo.entity.KqStaffEntity;
import com.example.springdemo.entity.KqYearVacationEntity;
import com.example.springdemo.mapper.KqStaffMapper;
import com.example.springdemo.mapper.KqYearVacationMapper;
import com.example.springdemo.service.VacationTypeService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-09 17:03
 * @Describe
 */

@Service
public class VacationTypeServiceImpl implements VacationTypeService {

    private  Double illFrequency = 0.5;
    private  Double illMax = 1.0;

    @Override
    public String parentingVacation() {
        return null;
    }

    @Override
    public String illVacation() {
        /**
         *  病假逻辑: 每个考勤周期内有0.5天病假, 病假大于 1时, 重置为1
         *
         *  请病假: 病假必须为病假类型
         *
         */
        Double illDay = 1.0;
        Double illDays = addIllvacation(illDay);
        String yearDay = yearVacation();
        System.out.println("年假: " + yearDay );

        return String.valueOf(illDays);
    }

    private double addIllvacation(Double illDay){
        DateTime dateTime = new DateTime("2022-03-15");
        Integer day = dateTime.getDayOfMonth();
        if(day == 15 && illDay < illMax){
            illDay  = illDay + illFrequency;
        }
        return illDay;
    }

    /**
     * 请病假 减假期
     * @param type
     * @param day
     * @param staffCode
     * @return
     */
    public double minusIllVacation(String type,Double day, String staffCode){
        // 判断假期类型是否为病假   , 请假天数
        if(StringUtils.isEmpty(type) || day <= 0){
            return Double.parseDouble(null);
        }

        // 当前该用户有的病假
        Double illTotal = 1.0;
        // 入不敷出  告诉用户
        if(illTotal <  day){
            return Double.parseDouble(null);
        }

        illTotal  = illTotal - day;

        return illTotal;

    }

    /**
     * 销病假
     * @param type
     * @param day
     * @param staffCode
     * @return
     */
    public double plusIllVacation(String type,Double day, String staffCode){
        // 判断假期类型是否为病假   , 请假天数
        if(StringUtils.isEmpty(type) || day <= 0){
            return Double.parseDouble(null);
        }

        // 当前该用户有的病假
        Double illTotal = 1.0;

        if(illTotal < illMax){
            illTotal  = illTotal + day;
        }

        if(illTotal >= illMax){
            return illMax;
        }
        return illTotal;

    }

    /**
     * 年假
     * @return
     */

    @Override
    public String yearVacation() {

        // 员工入职日期
        String hird = "2022-01-01";
        String currDate = "2022-12-31";

        // 最小
        Integer min = 0;
        Integer max = 10;
        Double legalDay = 0.0;
        Double wealDay  = 10.0;
        Double maxDay   = 0.9;
        Double oneDay   = 1.0;
        Double halfDay  = 0.5;
        Double yearDay = 0.0;


        DateTime end   = new DateTime(currDate);
        DateTime start = new DateTime(hird);
        Period p = new Period(start,end, PeriodType.days());
        int day  = p.getDays() + 1;
        int year = p.getYears();
        if( year >= min && year <max  ){
            yearDay = ((float) day /365) * (legalDay + wealDay);
        }
        Double yearF = Math.floor(yearDay);
        Double f = yearDay - yearF;
        Double yearDecimal  = 0.0;
        if(f >= halfDay && f < maxDay){
            yearDecimal = halfDay;
        }else if (f >= maxDay){
            yearDecimal = oneDay;
        }

        yearDay = yearF + yearDecimal;

        timingYearVacation();
        return String.valueOf(yearDay);
    }

    /**
     * 请年假
     * 1. 请年假
     *  直到假期当天, 前端显示才会真正的减
     *
     */
    public  String plusYearVacation(Double day, String staffCode){
        if (day <= 0 && StringUtils.isEmpty(staffCode)){
            return "";
        }


        return "";
    }

    /**
     * 销年假
     */
    public  String minusYearVacation(Double day, String staffCode){
        if (day <= 0 && StringUtils.isEmpty(staffCode)){
            return "";
        }

        return "";
    }

    /**
     * 导入年假
     */

    /**
     * 年假延期
     */

    @Autowired
    KqStaffMapper kqStaffMapper;

    @Autowired
    KqYearVacationMapper kqYearVacationMapper;
    /**
     * 定时任务, 每天早上5点更新一下 年假
     */
    public void  timingYearVacation(){
        // 获取所有在职员工列表
        List<KqStaffEntity> kqStaffEntities = kqStaffMapper.queryAllStaff(1,10);
        List<KqYearVacationEntity> kqYearVacationEntities = kqYearVacationMapper.queryAllYearVacation();

        DateTime end   = new DateTime();
        Double halfDay = 0.5;
        Double maxDay = 0.9;
        for (KqStaffEntity kq: kqStaffEntities) {

            DateTime start = new DateTime(kq.getHireDate());
            Period p = new Period(start,end, PeriodType.days());
            int day  = p.getDays()%365 + 1;
            int year =  p.getDays()/365;
            Double yearDay = 0.0;
            for (KqYearVacationEntity kqYear:kqYearVacationEntities) {
                if( year >= kqYear.getMin() && year < kqYear.getMax()  ){
                    yearDay = ((float) day /365) * (kqYear.getLegalDay() + kqYear.getWealDay());
                    break;
                }
            }
            Double yearF = Math.floor(yearDay);
            Double f = yearDay - yearF;
            Double yearDecimal  = 0.0;
            if(f >= halfDay && f < maxDay){
                yearDecimal = halfDay;
            }
            yearDay = yearF + yearDecimal;
            System.out.println(yearDay);



        }



    }

}
