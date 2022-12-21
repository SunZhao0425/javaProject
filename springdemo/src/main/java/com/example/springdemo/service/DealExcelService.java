package com.example.springdemo.service;

import com.example.springdemo.dto.resp.BaseRespDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-06-09 15:53
 * @Describe
 */
public interface DealExcelService {

    BaseRespDto importExcelData(MultipartFile file);

    BaseRespDto exportExcelData(Map<String,Object> map, HttpServletRequest request, HttpServletResponse response);
}
