package com.example.springdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.springdemo.dto.resp.BaseRespDto;
import com.example.springdemo.service.DealExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-06-09 15:53
 * @Describe
 */

@Slf4j
@Service
public class DealExcelServiceImpl  implements DealExcelService {


    @Override
    public BaseRespDto importExcelData(MultipartFile file) {
        if(file.isEmpty()){
            return  null ;
        }
        ArrayList<JSONObject> list = new ArrayList<>();
        try{
            InputStream is = file.getInputStream();
            List<JSONObject> checkSpeakerList = new ArrayList<>();
            // 1. 创建工作簿
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            // 1.1: 创建工作表
            XSSFSheet sheet = null;
            // 1.2: 创建行
            XSSFRow row = null;

            // 1.3. 获取excel的工作表总数
            int sheetNum = workbook.getNumberOfSheets();
            //2.遍历Excel中所有的sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                // 2.1 获取某个工作表
                sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }

                ArrayList<String> errArr = new ArrayList<>();
                ArrayList<String> blackArr = new ArrayList<>();
                list = new ArrayList<>();
                // 3.遍历当前sheet中的所有行(从第二行开始，序号为1)
                for (int j = 0; j < sheet.getLastRowNum()+ 1; j++) {
                    row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    JSONObject jsonObject = new JSONObject();
                    // 获取每行数据的第一列
                    jsonObject.put("var1", row.getCell(0).getNumericCellValue());
                    // 获取每行数据的第二列
                    jsonObject.put("var2", row.getCell(1).getStringCellValue());
                    list.add(jsonObject);
                }

                log.error("导入异常员工: {}" + list);
            }
            return BaseRespDto.successResp(list);
        }catch (Exception e){
            return BaseRespDto.errorResp("导入数据异常"+ e.getMessage());
        }
    }

    @Override
    public BaseRespDto exportExcelData(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response) {
        String filename = "猎头数据";
        XSSFSheet sheet;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xlsx");

        XSSFWorkbook book = new XSSFWorkbook();
        sheet = book.createSheet("sheet1");
        List<String> titles = (List<String>) model.get("titles");
        int len = titles.size();
        XSSFCellStyle headerStyle = book.createCellStyle(); //标题样式
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        short height = 25 * 20;
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < len; i++) { //设置标题
            String title = titles.get(i);
            row.setRowStyle(headerStyle);
            row.createCell(i).setCellValue(title);
        }
        sheet.getRow(0).setHeight(height);

        XSSFCellStyle contentStyle = book.createCellStyle(); //内容样式
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        List<JSONObject> varList = (List<JSONObject>) model.get("varList");
        int varCount = varList.size();
        for (int i = 0; i < varCount; i++) {
            JSONObject vpd = varList.get(i);
            XSSFRow rows = sheet.createRow(i + 1);
            for (int j = 0; j < len; j++) {
                String varstr = vpd.get("var" + (j + 1)) != null ? String.valueOf(vpd.get("var" + (j + 1))) : "";
                rows.createCell(j).setCellValue(varstr);
            }

        }
        return null;
    }
}
