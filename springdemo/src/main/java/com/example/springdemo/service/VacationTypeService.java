package com.example.springdemo.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-03-09 17:02
 * @Describe
 */


public interface VacationTypeService {
    String parentingVacation();

    String illVacation();

    String yearVacation();

    int importCheckSpeaker(MultipartFile file) throws Exception;
}
