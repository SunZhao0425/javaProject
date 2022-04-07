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
@TableName("sys_job")
public class JobEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "nick_name")
    private String nickName;

    private String username;
    private String gender;
    private String phone;
    private String email;
    private String enabled;

    @Column(name = "avatar_name")
    private String avatarName;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "pwd_reset_time")
    @ApiModelProperty(value = "最后修改密码的时间", hidden = true)
    private Date pwdResetTime;
}
