package com.shiye.mir.service.impl;

import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.SeparatedService;
import com.shiye.mir.utils.CommonUtils;
import com.shiye.mir.utils.CompressDirUtil;
import com.shiye.mir.utils.WordsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 主功能实现类
 * @author fangshaozu
 */
@Slf4j
@Service
public class SeparatedServiceImpl implements SeparatedService {

    @Resource
    private ScriptsServiceImpl scriptsService;

    @Value("${file.save.path}")
    private String savePath;

    @Value("${file.save.root}")
    private String rootPath;

    @Value("${file.save.suffix}")
    private String suffix;

    @Value("${file.save.env}")
    private String env;

    @Override
    public EnumResponseCode uploadMusic(MultipartFile file,String userId) {
        String originalName = WordsUtils.blankToUnder(file.getOriginalFilename());
        if (file.isEmpty()) {
            return EnumResponseCode.FILE_UP_FAILED;
        }
        if (!CommonUtils.checkSize(file)){
            return EnumResponseCode.FILE_UP_TOO_BIG;
        }
        //0.文件存在根目录中
        File dest = new File(savePath + originalName);
        try {
            file.transferTo(dest);
            log.info("UPLOAD Music success, fileName is :{}",originalName);
            return doSeparate(userId,originalName);
        } catch (IOException e) {
            log.error("UPLOAD Music failed because of IOException", e);
        }
        return EnumResponseCode.UNKNOWN_ERROR;
    }

    public EnumResponseCode doSeparate(String userId,String fileName){
        //1.生成脚本文件在根目录
        if("dev".equals(env)){
            scriptsService.generateBat(fileName);
        }else{
            scriptsService.generateShell(fileName);
        }
        //2.执行脚本，生成文件夹
        EnumResponseCode modelResult = scriptsService.scriptExec(
                savePath+ WordsUtils.suffixAbortion(fileName)+suffix);
        if(modelResult == EnumResponseCode.FILE_SEPARATE_FAILED){
            return modelResult;
        }
        //3.压缩文件夹
        if(!CompressDirUtil.compressFileToZip(savePath+rootPath+WordsUtils.suffixAbortion(fileName))){
            return EnumResponseCode.FILE_COMPRESS_FAILED;
        }else{
            return EnumResponseCode.FILE_COMPRESS_SUCCESS;
        }
    }

    @Override
    public void downLoadFile( String fileName, HttpServletResponse response){
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(savePath
                    + rootPath + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
