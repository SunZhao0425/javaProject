package com.example.springdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-15 17:37
 * @Describe
 */

@Entity
@Getter
@Setter
@TableName("kq_year_vacation")
public class KqYearVacationEntity extends KqBaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "min")
    private Integer min;

    @Column(name = "max")
    private Integer max;

    @Column(name = "legal_day")
    private Double legalDay;

    @Column(name = "weal_day")
    private Double wealDay;
}