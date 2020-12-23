package com.shiye.mir.service.impl;

import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.SeparatedService;
import com.shiye.mir.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 主功能实现类
 * @author fangshaozu_sx
 */
@Slf4j
@Service
public class SeparatedServiceImpl implements SeparatedService {

    @Value("${file.save.path}")
    private String fileSavePath;

    @Override
    public EnumResponseCode uploadMusic(MultipartFile file,String userId) {
        if (file.isEmpty()) {
            return EnumResponseCode.FILE_UP_FAILED;
        }
        if (!CommonUtils.checkSize(file)){
            return EnumResponseCode.FILE_UP_TOO_BIG;
        }
        File dest = new File(fileSavePath + file.getOriginalFilename());
        try {
            file.transferTo(dest);
            log.info("UPLOAD Music success, fileName is :{}",file.getOriginalFilename());
            return doSeparate(userId);
        } catch (IOException e) {
            log.error("UPLOAD Music failed because of IOException", e);
        }
        return EnumResponseCode.UNKNOWN_ERROR;
    }

    public EnumResponseCode doSeparate(String userId){
        //执行脚本
        return null;
    }

    @Override
    public void downLoadFile( String fileName, HttpServletResponse response){
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        //文件读取和返回
        try {
            os = response.getOutputStream();
            //文件读取，之后会替换成redis
            bis = new BufferedInputStream(new FileInputStream(new File("F:/TestMIR/"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                //从redis拿出来的数据最后返回给用户
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
