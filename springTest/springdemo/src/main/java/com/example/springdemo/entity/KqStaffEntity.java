package com.example.springdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 16:12
 * @Describe
 */
@Entity
@Getter
@Setter
@TableName("kq_staff")
public class KqStaffEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "domain_id")
    private String domainId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "hire_date")
    private String hireDate;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "dept_id")
    private String deptId;

    @Column(name = "department_description")
    private String departmentDescription;

    /**
     * 直接上级
     */
    @Column(name = "supervisor_id")
    private String supervisorId;
    /**
     * 直接上级电话
     */
    @Column(name = "supervisor_phone")
    private String supervisorPhone;

    @Column(name = "location")
    private int location;

    @Column(name = "status")
    private int status;

    @Column(name = "company")
    private Long company;

    @Column(name = "empl_class")
    private Long emplClass;

}
