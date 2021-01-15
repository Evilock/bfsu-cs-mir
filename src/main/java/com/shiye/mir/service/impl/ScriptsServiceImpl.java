package com.shiye.mir.service.impl;

import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.ScriptsService;
import com.shiye.mir.utils.WordsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 脚本执行实现类
 * @author fangshaozu
 */
@Slf4j
@Service
public class ScriptsServiceImpl implements ScriptsService {


    @Value("${file.save.path}")
    private String savePath;

    @Override
    public void generateShell(String fileName) {
        String batPath = savePath+ WordsUtils.suffixAbortion(fileName) +".sh";
        File file = new File(batPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("#!/bin/sh");
            bw.newLine();
            bw.write("conda activate music");
            bw.newLine();
            bw.write("conda activate base");
            bw.newLine();
            bw.write("spleeter separate -i "+fileName+" -p spleeter:2stems -o output");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            log.error("generateBat",e);
        }
    }

    @Override
    public EnumResponseCode scriptExec(String path){
        Process p;
        InputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        try {
            p = Runtime.getRuntime().exec(path);
            fis=p.getInputStream();
            isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            String line;
            while((line=br.readLine())!=null) {
                log.info("script Execute:{}",line);
            }
            return EnumResponseCode.FILE_SEPARATE_SUCCESS;
        } catch (IOException e) {
            log.error("script Exec IOException happened",e);
            return EnumResponseCode.FILE_SEPARATE_FAILED;
        }
    }

    @Override
    public void generateBat(String fileName) {
        String batPath = savePath+ WordsUtils.suffixAbortion(fileName) +".bat";
        String filePath = savePath+fileName;
        File file = new File(batPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("C:");
            bw.newLine();
            bw.write("cd "+savePath);
            bw.newLine();
            bw.write("CALL activate music");
            bw.newLine();
            bw.write("CALL spleeter separate -i "+filePath+" -p spleeter:2stems -o output");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            log.error("generateBat",e);
        }
    }

    @Override
    public EnumResponseCode shellExec(String shellPath) {
        return null;
    }
}
