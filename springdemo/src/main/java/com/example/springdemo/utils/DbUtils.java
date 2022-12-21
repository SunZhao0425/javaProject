package com.example.springdemo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.springdemo.enums.PsOfferStatusEnum;
import com.example.springdemo.enums.ResumeChannelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import dm.jdbc.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-06-28 18:46
 * @Describe
 */

@Slf4j
public class DbUtils {

    private static final String URL = "jdbc:mysql://10.225.3.97:21556/zhaopin?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false";
    private static final String NAME = "zhaopin_r";
    private static final String PASSWORD = "67e6c2344ef65884";

    public static void main(String[] args) {

//        ArrayList<JSONObject> a = new ArrayList<>();
//        JSONObject j = new JSONObject();
//        JSONObject j1 = j;
//        a.add(j);
//        a.add(j1);
//        ArrayList<JSONObject> b = new ArrayList<>();
//        b.add(j);
//        a.removeAll(b);
//        System.out.println(a);

//        JSONArray jsonArray = queryResumeDataHeadhunting();
//        System.out.println(jsonArray);
    }

    /**
     * 2022年1月1日~9月15日，发起offer且入职的猎头渠道的人的信息
     * @return
     */
    public static JSONArray queryResumeDataHeadhunting(HttpServletResponse response) {
        JSONArray jsonArray = new JSONArray();
        String bolesql = String.format("select  * from zhaopin.offer_flow_approve_log where create_time \n" +
                "BETWEEN 1640966400000 and 1663257600000   and ps_status in (200,304) and inst_Id != '' ORDER BY id DESC");

        ArrayList<JSONObject> boleList = getOfferQuery(bolesql);
        for (JSONObject o : boleList) {
            JSONObject jsonObject = new JSONObject();
            String request_data = o.getString("request_data");
            JSONObject request_data_json = (JSONObject) JSONObject.parse(request_data);
            JSONObject resumeJ = request_data_json.getJSONObject("resume");
            JSONObject offerJ = request_data_json.getJSONObject("offer");
            // 员工姓名
            jsonObject.put("v1", resumeJ.getString("name"));
            // 对内职位名称
            jsonObject.put("v2", offerJ.getString("job_internal_title"));
            // 职级
            jsonObject.put("v3", offerJ.getString("job_rank"));
            // 猎头公司
            JSONObject huntCompany = offerJ.getJSONObject("belloHuntCompanyOfferReq");
            jsonObject.put("v4", huntCompany.getString("name"));
            // 猎头费率
            jsonObject.put("v5", "20%");
            // 猎头费
            jsonObject.put("v6", offerJ.getString("headhunterFeeAmountTotal"));
            // 所属部门
            JSONObject departJ = offerJ.getJSONObject("demand_team");
            String id = departJ.getString("third_party_id");
            String departSql = String.format("SELECT  d5.department as department2, d4.department as department1, CONCAT(d3.department,'/',d2.department ,'/',d1.department) as department0 , d1.code" +
                    " FROM  ps_department d1" +
                    " LEFT JOIN ps_department  d2  on d2.code = d1.pcode" +

                    " LEFT JOIN ps_department  d3  on d3.code = d2.pcode" +

                    " LEFT JOIN ps_department  d4  on d4.code = d3.pcode" +

                    " LEFT JOIN ps_department  d5  on d5.code = d4.pcode" +
                    " where d1.code = '%s'", id);
            JSONObject departmentJ = getDepartQuery(departSql);
            if (!ObjectUtils.isEmpty(departmentJ)) {
                jsonObject.put("v7", departmentJ.getString("department2") + "/" + departmentJ.getString("department1") + "/" + departmentJ.getString("department"));
            }
            JSONObject jobJ = request_data_json.getJSONObject("job");
            JSONObject subsequenceJ = jobJ.getJSONObject("subsequence");
            // 职位序列
            jsonObject.put("v8", subsequenceJ.getString("major") + "/" + subsequenceJ.getString("minor")
                    + "/" + subsequenceJ.getString("detail"));
            // 职位负责人
            jsonObject.put("v9", o.getString("name"));
            // 提交offer时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
            jsonObject.put("v10", sdf.format(o.getLong("create_time")));

            jsonArray.add(jsonObject);
        }
        try{
            exportExcelFile(jsonArray,response);
        }catch (Exception e){

        }

        return jsonArray;
    }

    /**
     * 导出excel 文件
     * @return
     */
    public static void exportExcelFile(JSONArray jsonArray, HttpServletResponse response) throws ServletException {

        try {
            String sheetName = "2022年1月1日_10月26日+发起offer信息";
            // excel的文档对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // excel的表单   sheet的名称
            HSSFSheet sheet  = wb.createSheet(sheetName);
            // excel字体
            HSSFFont font = wb.createFont();
            // cell样式
            HSSFCellStyle style = wb.createCellStyle();
            //表头
//            String[] titles = {"员工名称","职位名称（对内","职级","猎头公司","猎头费率","猎头费","所属部门","职位序列","职位负责人","提交offer时间" };
            String[] titles = {"候选人名称","职位名称（对内)","职级","学历","学校","职位名称","简历渠道","职位序列","职位负责人","提交offer时间","渠道","审批结果","简历来源","部门名称"};
            HSSFRow rowTitle = sheet.createRow(0);
            for (int i=0; i<titles.length; i++) {
                rowTitle.createCell(i).setCellValue(titles[i]);
            }

            //  在第二行插数据
            for (int i = 0; i < jsonArray.size(); i++) {
                //获得第二行
                HSSFRow row = sheet.createRow(i + 1);
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //通过反射获得这个对象的属性字段数组
                row.createCell(0).setCellValue(jsonObject.getString("v1"));
                row.createCell(1).setCellValue(jsonObject.getString("v2"));
                row.createCell(2).setCellValue(jsonObject.getString("v3"));
                row.createCell(3).setCellValue(jsonObject.getString("v4"));
                row.createCell(4).setCellValue(jsonObject.getString("v5"));
                row.createCell(5).setCellValue(jsonObject.getString("v6"));
                row.createCell(6).setCellValue(jsonObject.getString("v7"));
                row.createCell(7).setCellValue(jsonObject.getString("v8"));
                row.createCell(8).setCellValue(jsonObject.getString("v9"));
                row.createCell(9).setCellValue(jsonObject.getString("v10"));
                row.createCell(10).setCellValue(jsonObject.getString("v11"));
                row.createCell(11).setCellValue(jsonObject.getString("v12"));
                row.createCell(12).setCellValue(jsonObject.getString("v13"));
                row.createCell(13).setCellValue(jsonObject.getString("v14"));


            }



            // 下载文件
            String fileName = sheetName + ".xls";
//            fileName = new String(fileName.getBytes(),"ISO8859-1");
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static JSONArray queryResumeDataNum() {
        JSONArray jsonArray = new JSONArray();
//        String bolesql = String.format("select  * from zhaopin.offer_flow_approve_log where create_time \n" +
//                "BETWEEN 1640966400000 and 1655222400000   and ps_status in (200,304) and inst_Id != '' ORDER BY id DESC");
        String bolesql = String.format("select  * from zhaopin.offer_flow_approve_log where name = '刘奕含'  ORDER BY id DESC");

        ArrayList<JSONObject> boleList = getOfferQuery(bolesql);
        for (JSONObject o : boleList) {
            JSONObject jsonObject = new JSONObject();
            String request_data = o.getString("request_data");
            JSONObject request_data_json = (JSONObject) JSONObject.parse(request_data);
            JSONObject resumeJ = request_data_json.getJSONObject("resume");
            JSONObject offerJ = request_data_json.getJSONObject("offer");

            // 员工姓名
            jsonObject.put("v1", resumeJ.getString("name"));
            // 对内职位名称
            jsonObject.put("v2", offerJ.getString("job_internal_title"));
            // 职级
            jsonObject.put("v3", offerJ.getString("job_rank"));
            // 猎头公司
            JSONObject huntCompany = offerJ.getJSONObject("belloHuntCompanyOfferReq");
            jsonObject.put("v4", huntCompany.getString("name"));
            // 猎头费率
            jsonObject.put("v5", "20%");
            // 猎头费
            jsonObject.put("v6", offerJ.getString("headhunterFeeAmountTotal"));
            // 所属部门
            JSONObject departJ = offerJ.getJSONObject("demand_team");
            String id = departJ.getString("third_party_id");
            String departSql = String.format("SELECT  d5.department as department2, d4.department as department1, CONCAT(d3.department,'/',d2.department ,'/',d1.department) as department0 , d1.code" +
                    " FROM  ps_department d1" +
                    " LEFT JOIN ps_department  d2  on d2.code = d1.pcode" +

                    " LEFT JOIN ps_department  d3  on d3.code = d2.pcode" +

                    " LEFT JOIN ps_department  d4  on d4.code = d3.pcode" +

                    " LEFT JOIN ps_department  d5  on d5.code = d4.pcode" +
                    " where d1.code = '%s'", id);
            JSONObject departmentJ = getDepartQuery(departSql);
            if (!ObjectUtils.isEmpty(departmentJ)) {
                jsonObject.put("v7", departmentJ.getString("department2") + "/" + departmentJ.getString("department1") + "/" + departmentJ.getString("department"));
            }
            JSONObject jobJ = request_data_json.getJSONObject("job");
            JSONObject subsequenceJ = jobJ.getJSONObject("subsequence");
            // 职位序列
            jsonObject.put("v8", subsequenceJ.getString("major") + "/" + subsequenceJ.getString("minor")
                    + "/" + subsequenceJ.getString("detail"));
            // 职位负责人
            jsonObject.put("v9", o.getString("name"));
            // 提交offer时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
            jsonObject.put("v10", sdf.format(o.getLong("create_time")));

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 查询提交过offer的信息
     * @return
     */
    public static JSONArray querySumbit() {
        JSONArray jsonArray = new JSONArray();
        String bolesql = String.format("select  * from zhaopin.offer_flow_approve_log where name in ('刘立雄','刘畅','刘奕含','丁大伟','牛欣旗','吴亚平','刘小磊','王丹','马倩云','方慧','肖丝婷')  \n" +
                "and create_time  BETWEEN 1653235200000 and 1661529600000 ORDER BY id DESC ");

        ArrayList<JSONObject> boleList = getOfferQuery(bolesql);
        for (JSONObject o : boleList) {
            JSONObject jsonObject = new JSONObject();
            String request_data = o.getString("request_data");
            JSONObject request_data_json = (JSONObject) JSONObject.parse(request_data);
            JSONObject resumeJ = request_data_json.getJSONObject("resume");
            JSONObject offerJ = request_data_json.getJSONObject("offer");
            // 员工姓名
            jsonObject.put("v1", resumeJ.getString("name"));
            // 对内职位名称
            jsonObject.put("v2", offerJ.getString("job_internal_title"));
            // 职级
            jsonObject.put("v3", "kong");
            jsonObject.put("v4","kong");
            jsonObject.put("v5","kong");
            // 学历
            jsonObject.put("v4", resumeJ.getString("topEduDegree"));
            // 学校
            jsonObject.put("v5", resumeJ.getString("lastSchool"));
            // 对外职位名称
            jsonObject.put("v6", offerJ.getString("jobTitle"));
////            // 简历来源
//            jsonObject.put("v7", resumeJ.getString("sourceChannel"));
//            // 简历上传
//            jsonObject.put("v13", resumeJ.getString("importType"));
            // 猎头公司
//            JSONObject huntCompany = offerJ.getJSONObject("belloHuntCompanyOfferReq");
//            jsonObject.put("v4", huntCompany.getString("name"));
//            // 猎头费率
//            jsonObject.put("v5", "20%");
//            // 猎头费
//            jsonObject.put("v6", offerJ.getString("headhunterFeeAmountTotal"));
            // 所属部门
            JSONObject departJ = offerJ.getJSONObject("demand_team");
            String id = departJ.getString("third_party_id");
            String departSql = String.format("SELECT  d5.department as department2, d4.department as department1, CONCAT(d3.department,'/',d2.department ,'/',d1.department) as department0 , d1.code" +
                    " FROM  ps_department d1" +
                    " LEFT JOIN ps_department  d2  on d2.code = d1.pcode" +

                    " LEFT JOIN ps_department  d3  on d3.code = d2.pcode" +

                    " LEFT JOIN ps_department  d4  on d4.code = d3.pcode" +

                    " LEFT JOIN ps_department  d5  on d5.code = d4.pcode" +
                    " where d1.code = '%s'", id);
            JSONObject departmentJ = getDepartQuery(departSql);
            if (!ObjectUtils.isEmpty(departmentJ)) {
                jsonObject.put("v7", departmentJ.getString("department2") + "/" + departmentJ.getString("department1") + "/" + departmentJ.getString("department"));
            }
            JSONObject jobJ = request_data_json.getJSONObject("job");
            JSONObject subsequenceJ = jobJ.getJSONObject("subsequence");
//            // 职位序列
//            jsonObject.put("v8", subsequenceJ.getString("major") + "/" + subsequenceJ.getString("minor")
//                    + "/" + subsequenceJ.getString("detail"));
            // 职位负责人
            jsonObject.put("v9", o.getString("name"));
            // 提交offer时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
            jsonObject.put("v10", sdf.format(o.getLong("create_time")));
//            jsonObject.put("v11", "");
            jsonObject.put("v12", "kong");
            jsonObject.put("v13", "kong");
            jsonObject.put("v14", "kong");
            jsonObject.put("v15", "kong");
            jsonObject.put("v16", "kong");
            jsonObject.put("v17", "kong");
            jsonObject.put("v18", "kong");
//            if (!ObjectUtils.isEmpty(ResumeChannelEnum.getDesc(o.getString("resume_channel")))) {
//                // 渠道
//                jsonObject.put("v11", ResumeChannelEnum.getDesc(o.getString("resume_channel")).getDesc());
//            }
            if ( !ObjectUtils.isEmpty(PsOfferStatusEnum.getValue(o.getString("ps_status")))) {
                // offer 状态
                jsonObject.put("v12", PsOfferStatusEnum.getValue(o.getString("ps_status")).getDesc());

                if (o.getString("ps_status").equals("200")){
                    jsonObject.put("v13", sdf.format(o.getLong("finish_time")));
                }

                if (o.getString("ps_status").equals("404")){
                    jsonObject.put("v14", sdf.format(o.getLong("update_time")));
                }
            }
            JSONArray interviews = request_data_json.getJSONArray("interviews");
            for(int i = 0; i < interviews.size(); i++ ){
                JSONObject interview = interviews.getJSONObject(i);
                if (i == 0){
                    jsonObject.put("v15", sdf.format(interview.getJSONArray("interviewerSchedule").getJSONObject(0).getLong("start_time")));
                }
                if (i == 1){
                    jsonObject.put("v16", sdf.format(interview.getJSONArray("interviewerSchedule").getJSONObject(0).getLong("start_time")));
                }
                if (i == 2){
                    jsonObject.put("v17", sdf.format(interview.getJSONArray("interviewerSchedule").getJSONObject(0).getLong("start_time")));
                }
                if (i == 3){
                    jsonObject.put("v18", sdf.format(interview.getJSONArray("interviewerSchedule").getJSONObject(0).getLong("start_time")));
                }

            }

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 查询提交过offer的信息
     * @return
     */
    public static JSONArray querySumbitOffer(HttpServletResponse response) {
        JSONArray jsonArray = new JSONArray();
        String bolesql = String.format("select  * from zhaopin.offer_flow_approve_log where name = '刘奕含'  ORDER BY id DESC");

        ArrayList<JSONObject> boleList = getOfferQuery(bolesql);
        for (JSONObject o : boleList) {
            JSONObject jsonObject = new JSONObject();
            String request_data = o.getString("request_data");
            JSONObject request_data_json = (JSONObject) JSONObject.parse(request_data);
            JSONObject resumeJ = request_data_json.getJSONObject("resume");
            JSONObject offerJ = request_data_json.getJSONObject("offer");
            // 员工姓名
            jsonObject.put("v1", resumeJ.getString("name"));
            // 对内职位名称
            jsonObject.put("v2", offerJ.getString("job_internal_title"));
            // 职级
            jsonObject.put("v3", offerJ.getString("job_rank"));
            jsonObject.put("v4","");
            jsonObject.put("v5","");
            // 学历
            jsonObject.put("v4", resumeJ.getString("topEduDegree"));
            // 学校
            jsonObject.put("v5", resumeJ.getString("lastSchool"));
            // 对外职位名称
            jsonObject.put("v6", offerJ.getString("jobTitle"));
//            // 简历来源
            jsonObject.put("v7", resumeJ.getString("sourceChannel"));
            // 简历上传
            jsonObject.put("v13", resumeJ.getString("importType"));

            JSONObject jobJ = request_data_json.getJSONObject("job");
            JSONObject subsequenceJ = jobJ.getJSONObject("subsequence");
            // 职位序列
            jsonObject.put("v8", subsequenceJ.getString("major") + "/" + subsequenceJ.getString("minor")
                    + "/" + subsequenceJ.getString("detail"));
            // 职位负责人
            jsonObject.put("v9", o.getString("name"));
            // 提交offer时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
            jsonObject.put("v10", sdf.format(o.getLong("create_time")));
            jsonObject.put("v11", "");
            jsonObject.put("v12", "");
            if (!ObjectUtils.isEmpty(ResumeChannelEnum.getDesc(o.getString("resume_channel")))) {
                // 渠道
                jsonObject.put("v11", ResumeChannelEnum.getDesc(o.getString("resume_channel")).getDesc());
            }
            if ( !ObjectUtils.isEmpty(PsOfferStatusEnum.getValue(o.getString("ps_status")))) {
                // offer 状态
                jsonObject.put("v12", PsOfferStatusEnum.getValue(o.getString("ps_status")).getDesc());
            }
            JSONObject departJ = offerJ.getJSONObject("demand_team");
            String id = departJ.getString("third_party_id");
            String departSql = String.format("SELECT  d5.department as department2, d4.department as department1, CONCAT(d3.department,'/',d2.department ,'/',d1.department) as department0 , d1.code" +
                    " FROM  ps_department d1" +
                    " LEFT JOIN ps_department  d2  on d2.code = d1.pcode" +

                    " LEFT JOIN ps_department  d3  on d3.code = d2.pcode" +

                    " LEFT JOIN ps_department  d4  on d4.code = d3.pcode" +

                    " LEFT JOIN ps_department  d5  on d5.code = d4.pcode" +
                    " where d1.code = '%s'", id);
            JSONObject departmentJ = getDepartQuery(departSql);
            if (!ObjectUtils.isEmpty(departmentJ)) {
                jsonObject.put("v14", departmentJ.getString("department2") + "/" + departmentJ.getString("department1") + "/" + departmentJ.getString("department"));
            }
            jsonArray.add(jsonObject);
        }
        try{
            exportExcelFile(jsonArray,response);
        }catch (Exception e){

        }
        return jsonArray;
    }


    /**
     * 获取部门查询
     *
     * @param queryString
     * @return
     */
    public static JSONObject getDepartQuery(String queryString) {
        try {
            JSONObject Sites = new JSONObject();
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryString);
            // 展开结果集数据库
            while (resultSet.next()) {
                // 通过字段检索
                String department = resultSet.getString("department0");
                String department1 = resultSet.getString("department1");
                String department2 = resultSet.getString("department2");
                Sites.put("department", department);
                Sites.put("department1", department1);
                Sites.put("department2", department2);
            }
            conn.close();
            return Sites;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取简历基本信息
     *
     * @param queryString
     * @return
     */
    public static ArrayList getOfferQuery(String queryString) {
        try {
            ArrayList list = new ArrayList();
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryString);
            // 展开结果集数据库
            while (resultSet.next()) {
                JSONObject Sites = new JSONObject();
                // 通过字段检索
                Integer id = resultSet.getInt("id");
                String request_data = resultSet.getString("request_data");
                String create_time = resultSet.getString("create_time");
                String update_time = resultSet.getString("update_time");
                String finish_time = resultSet.getString("finish_time");
                String name = resultSet.getString("name");
                String resume_channel = resultSet.getString("resume_channel");
                String ps_status = resultSet.getString("ps_status");
                Sites.put("id", id);
                Sites.put("request_data", request_data);
                Sites.put("create_time", create_time);
                Sites.put("update_time", update_time);
                Sites.put("finish_time", finish_time);
                Sites.put("name", name);
                Sites.put("resume_channel", resume_channel);
                Sites.put("ps_status", ps_status);
                list.add(Sites);
            }
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 jdbc 连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        return conn;
    }


    /**
     * 时间转换  datetime 格式转为 yyyy-MM-dd'T'HH:mm:ss'Z' 格式
     *
     * @param str
     * @return
     */
    public static String dateToChange(String str, Integer isWord) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        try {
            DateTimeFormatter sdf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter wordSdf = DateTimeFormat.forPattern("yyyy年MM月dd日");

            String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
            String wordPattern = "\\d{4}年\\d{1,2}月\\d{1,2}日";
            if (str.matches(datePattern) && !StringUtil.equals(str, "0000-00-00 00:00:00")) {
                DateTime dateTime = DateTime.parse(str, sdf);
                return dateTime.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
            } else if (isWord == 1 && str.matches(wordPattern)) {
                DateTime dateTime = DateTime.parse(str, wordSdf);
                return dateTime.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
        } catch (Exception e) {
            log.error("日期:{},格式转换错误:{},", str, e);
        }
        return str;
    }


    /**
     * 将时间戳转化为datetime格式
     * 原: 1641290108054
     * 目标: yyyy-MM-dd HH:mm:ss
     */
    public static String longToDatetime(Long time, String dateFormat) {
        try {
            if (StringUtil.isEmpty(dateFormat)) {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);//要转换的时间格式
            return sdf.format(time);
        } catch (Exception e) {
            log.error("日期转换异常");
            return null;
        }
    }

    /**
     * 导出成文件
     *
     * @param resumeArr
     * @throws IOException
     */
    private static void exportFiles(JSONArray resumeArr) throws IOException {
        String fileName = "E:\\倍罗系统相关\\resume0901-1.json";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter out = new BufferedWriter(fileWriter);
        for (int i = 0; i < resumeArr.size(); i++) {
            JSONObject resumeObj = (JSONObject) resumeArr.get(i);
            out.write(resumeObj.toJSONString());
            out.newLine();
        }
        out.flush();
        out.close();
    }

    /**
     * 导出成文件
     *
     * @param resumeObj
     * @throws IOException
     */
    private static void exportFile(JSONObject resumeObj) throws IOException {
        String fileName = "_" + resumeObj.get("resumeId");
        fileName = StringUtil.md5(fileName) + fileName;
        fileName = "E:\\path\\" + fileName + ".json";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter out = new BufferedWriter(fileWriter);
        out.write(resumeObj.toJSONString());
        out.close();
    }

}
