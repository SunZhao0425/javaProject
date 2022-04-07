package com.example.springdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 18:33
 * @Describe
 */

@Entity
@Getter
@Setter
@TableName("kq_staff_year_vacation")
public class kqStaffYearVacationEntity {

    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "legal_over_day")
    private Integer legalOverDay;

    @Column(name = "weal_over_day")
    private Integer wealOverDay;

    @Column(name = "temp_day")
    private Integer tempDay;

    @Column(name = "temp_expired")
    private Integer tempExpired;

    @Column(name = "forever_day")
    private Integer foreverDay;

    @Column(name = "forever_expired")
    private Integer foreverExpired;

    @Column(name = "year")
    private Integer year;

}
