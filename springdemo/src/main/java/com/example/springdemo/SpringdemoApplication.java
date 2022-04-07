package com.example.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;






@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = { "com.example.springdemo.*",})
@EnableAsync
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan(value = {"com.example.springdemo.mapper"})
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

}
