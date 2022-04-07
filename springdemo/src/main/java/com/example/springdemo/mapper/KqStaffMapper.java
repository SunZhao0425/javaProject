package com.example.springdemo.mapper;

import com.example.springdemo.entity.KqStaffEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 16:46
 * @Describe
 */


public interface KqStaffMapper {


    @Select("SELECT * FROM kq_staff limit  #{page}, #{pageSize}")
    List<KqStaffEntity> queryAllStaff(@Param("page") int page, @Param("pageSize") int pageSize);
}
