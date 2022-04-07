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
@TableName("sys_role")
public class RoleEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String name;
    private String level;
    private String description;

    @Column(name = "data_scope")
    private String dataScope;

}
