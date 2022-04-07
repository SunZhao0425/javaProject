package com.example.springdemo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-26 10:28
 * @Describe
 */

public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("SELECT * FROM sys_user su  left join sys_dept sd on sd.dept_id = su.dept_id ")
    List<UserEntity> getAllUser();

//    @Select("insert into ")
    @Insert({
            "insert into sys_user ("
    })
    int insertUser(UserEntity userEntity);

//    @Select("SELECT * FROM sys_user su  left join sys_dept sd on sd.dept_id = su.dept_id ")
//    List<UserEntity> getAllUser();
//    @Select("SELECT * FROM sys_user su  left join sys_dept sd on sd.dept_id = su.dept_id ")
//    List<UserEntity> getAllUser();

}
