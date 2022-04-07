package com.example.springdemo.mapper;


import com.example.springdemo.entity.PsUser;
import com.example.springdemo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

public interface PsUserMapper {

    @Select("SELECT * FROM sys_user where user_id = #{id}")
    UserEntity getUser(@Param("id") Integer id);

//
//    @Insert("INSERT INTO user (`name`,`role`) VALUES(#{name},#{role})")
//    int insert(PsUser psUser);
//
//
//    @Update("update user set `name`=#{name}, `role` = #{role}  where (id = #{id})")
//    int update(PsUser psUser);
//
//    @Select("SELECT * FROM sys_user limit #{page}, #{pageSize}")
//    List<UserEntity> getUserList(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
