package com.shiye.mir.service;


import com.shiye.mir.enums.EnumResponseCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 启动伴奏分离类
 * @author fangshaozu
 */
public interface SeparatedService {
    /**
     * 上传文件
     * @param file 文件
     * @param userId 用户ID
     * @return 结果字符串
     */
    EnumResponseCode uploadMusic(MultipartFile file, String userId);



    /**
     * 下载文件
     * @param fileName 文件名
     * @param response response
     */
    void downLoadFile(String fileName, HttpServletResponse response);
}
