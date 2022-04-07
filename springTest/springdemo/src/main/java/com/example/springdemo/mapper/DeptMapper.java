package com.example.springdemo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.entity.DeptEntity;
import com.example.springdemo.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-10-26 10:28
 * @Describe
 */

public interface DeptMapper extends BaseMapper<DeptEntity> {

    @Select("SELECT * FROM sys_dept where dept_id = #{deptId}")
    DeptEntity selectDeptById(Integer deptId);

}
