package com.example.springdemo.mapper;

import com.example.springdemo.entity.KqYearVacationEntity;
import com.example.springdemo.entity.kqStaffYearVacationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 16:46
 * @Describe
 */


public interface KqStaffYearVacationMapper {


    @Select("SELECT * FROM kq_staff_year_vacation")
    Integer insertYearVacation(KqYearVacationEntity kqYearVacationEntity);


    @Insert("SELECT * FROM kq_staff_year_vacation")
    Integer batchYearVacation(KqYearVacationEntity kqYearVacationEntity);


}
