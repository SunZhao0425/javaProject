package com.example.springdemo.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-02-18 11:28
 * @Describe
 *
 * 1.      新增/复制/移动/删除/文件详细 => 文件
 * 2. 批量  新增/移动/删除     => 文件
 *
 * 3.新增  文件夹
 * 4.复制/移动/删除   文件夹 及 文件夹下的文件夹和文件
 * 5.重命名
 * 6.上传 , 上传多个文件
 *
 */

@Slf4j
public class FileUtils {

    public static void  dealFile(String path) throws IOException {
//        File file = new File(path);
//        // 是否存在
//        if(file.isDirectory()){
//
//        }else{
//            file.mkdir();
//        }
//        log.info("file: {}",file);

    }

    /**
     * 上传文件
     *
     */
    public static void  uploadFile(){
        log.info("上传文件: ");
    }

    /**
     * 新增文件夹
     *  mkdirs()可以建立多级文件夹，
     *  mkdir()只会建立一级的文件夹.
     */
    public static void insertFolder(String filePath) throws IOException {
        String folderName = filePath + "新建文件夹";
        File folder = new File(folderName);

        if (!folder.exists()) {
            folder.setWritable(true);
            folder.mkdir();
        }else {
            log.info("新建文件夹已经存在");
            String reFolderName = filePath + "新建文件夹_副本";
            File reFolder = new File(reFolderName);
            reFolder.mkdir();
        }

    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            log.error("复制单个文件操作出错: {}", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            log.error("复制整个文件夹内容操作出错: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 移动文件
     * @param oldPath
     * @param newPath
     */
    public static void moveFolder(String oldPath, String newPath){
        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
            deleteDirectory(oldPath);
        } catch (Exception e) {
            log.error("复制整个文件夹内容操作出错: {}", e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 删除单个文件
     *
     * @param fileName：要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFileOne(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir：要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFileOne(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static boolean recomeTo(String dir){
        File file = new File(dir);
        if(file.exists()){
            file.renameTo(new File("ces"));
        }
        return true;
    }


    @SneakyThrows
    public static void main(String[] args) {
//        dealFile("E://interview.json");
        String fileName = "E:/path1/";
        String fileName1 = "E:/interview.json";

//        insertFolder(fileName);
//        copyFile(fileName1,fileName + "interview4.json");
        copyFolder(fileName, "E:/path2/");
        moveFolder("E:/path2/", "E:/path3/");

    }
}
