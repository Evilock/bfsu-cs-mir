package com.shiye.mir.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 常规Utils类
 * @author fangshaozu
 */
@Slf4j
public class CommonUtils {

    public static Long MAXIMUM_FILE_SIZE = 10485760L;

    /**
     * 随机生成一个UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    /**
     * 邮箱有效性验证
     */
    public static boolean checkEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(email);
        return m.matches();
    }

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

}
