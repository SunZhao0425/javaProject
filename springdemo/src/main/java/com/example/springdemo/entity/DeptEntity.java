package com.example.springdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2021-12-10 14:55
 * @Describe
 */

@Entity
@Getter
@Setter
@TableName("sys_dept")
public class DeptEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(name = "sub_count")
    private String subCount;

    private Long pid;
    private String name;
    private String enabled;

    @Column(name = "dept_sort")
    private String deptSort;


}
