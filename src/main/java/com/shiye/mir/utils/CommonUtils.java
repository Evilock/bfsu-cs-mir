package com.shiye.mir.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Slf4j
public class CommonUtils {

    public static Long MAXIMUM_FILE_SIZE = 10485760L;

    /**
     * 文件大小控制
     */
    public static Boolean checkSize(MultipartFile f){
        if (!f.isEmpty()){
            return f.getSize() <= MAXIMUM_FILE_SIZE;
        }else{
            log.warn("file doesn't exist or is not a file");
            return false;
        }
    }

    /**
     * 随机生成一个UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
