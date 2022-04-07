package com.example.springdemo.mapper;

import com.example.springdemo.entity.KqYearVacationEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 16:46
 * @Describe
 */


public interface KqYearVacationMapper {


    @Select("SELECT * FROM kq_year_vacation")
    List<KqYearVacationEntity> queryAllYearVacation();
}
