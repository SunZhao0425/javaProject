package com.example.springdemo.controller;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-06-08 17:53
 * @Describe
 */


import com.example.springdemo.common.support.ResultWrap;
import com.example.springdemo.dto.resp.BaseRespDto;
import com.example.springdemo.service.DealExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 上传excel 文件; 将数据导入
 * 2. 将数据导出到 excel 文件, 文件保存到项目中
 *
 * 如果是xls，使用HSSFWorkbook；如果是xlsx，使用XSSFWorkbook
 */
@Slf4j
@Api(tags = "处理excel文件")
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class DealExcelController {

    @Autowired
    DealExcelService dealExcelService;

    @ApiOperation(value = "上传excel文件,导入数据", notes = "上传excel文件,导入数据")
    @PostMapping("/import")
    public Object importExcelData(@RequestParam("file") MultipartFile file,
                                      HttpServletRequest request, HttpServletResponse response) {
        if (file.isEmpty() && file.getName().indexOf("xls") > -1) {
            return ResultWrap.error(-1,-1,"请检查上传文件, 文件为空");
        }
        try {
            BaseRespDto baseRespDto = dealExcelService.importExcelData(file);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResultWrap.ok();
    }

    @ApiOperation(value = "导出数据", notes = "导出数据, 下载excel")
    @PostMapping("/export")
    public Object exportExcelData(HttpServletRequest request, HttpServletResponse response) {
        try {
            //导出的数据
            Map<String, Object> map = new HashMap<>();
            // 标题列表
            map.put("titles",new ArrayList<>());
            // 数据列表
            map.put("varList",new ArrayList<>());
            BaseRespDto baseRespDto = dealExcelService.exportExcelData(map,request,response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResultWrap.ok();
    }

}
