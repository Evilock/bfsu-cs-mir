package com.shiye.mir.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 文件夹压缩工具类
 * @author fangshaozu_sx
 */
public class CompressDirUtil {

    public static boolean compressFileToZip(String compressPath) {
        boolean bool = false;
        try {
            ZipOutputStream zipOutput = null;
            File file = new File(compressPath);
            if(file.isDirectory()){
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compressPath + ".zip")));
                //递归压缩文件夹，最后一个参数传""压缩包就不会有当前文件夹；传file.getName(),则有当前文件夹;
                compressZip(zipOutput, file, "");
            }else{
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compressPath.substring(0, compressPath.lastIndexOf(".")) + ".zip")));
                //压缩单个文件
                zipOFile(zipOutput, file);
            }
            zipOutput.closeEntry();
            zipOutput.close();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    private static void compressZip(ZipOutputStream zipOutput, File file, String suffixpath) {
        // 列出所有的文件
        File[] listFiles = file.listFiles();
        for(File fi : listFiles){
            if(fi.isDirectory()){
                if("".equals(suffixpath)){
                    compressZip(zipOutput, fi, fi.getName());
                }else{
                    compressZip(zipOutput, fi, suffixpath + File.separator + fi.getName());
                }
            }else{
                zip(zipOutput, fi, suffixpath);
            }
        }
    }

    public static void zip(ZipOutputStream zipOutput, File file, String suffixpath) {
        try {
            ZipEntry zEntry = null;
            if("".equals(suffixpath)){
                zEntry = new ZipEntry(file.getName());
            }else{
                zEntry = new ZipEntry(suffixpath + File.separator + file.getName());
            }
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read;
            while((read = bis.read(buffer)) != -1){
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void zipOFile(ZipOutputStream zipOutput, File file) {
        try {
            ZipEntry zEntry = new ZipEntry(file.getName());
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = bis.read(buffer)) != -1){
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}