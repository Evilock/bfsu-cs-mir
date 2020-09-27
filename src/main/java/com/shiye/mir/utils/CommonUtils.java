package com.shiye.mir.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
public class CommonUtils {

    public static Long MAXIMUM_FILE_SIZE = 10485760L;

    public static Boolean checkSize(MultipartFile f){
        if (!f.isEmpty()){
            return f.getSize() <= MAXIMUM_FILE_SIZE;
        }else{
            log.warn("file doesn't exist or is not a file");
            return false;
        }
    }

}
