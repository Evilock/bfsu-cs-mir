package com.shiye.mir.service.impl;

import com.shiye.mir.service.UploadFileService;
import com.shiye.mir.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public String uploadMusic(MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        if (!CommonUtils.checkSize(file)){
            return "文件太大，请传送小一点的!";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "F:/TestMIR/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error("upload#Exception", e);
        }
        return "上传失败！";
    }

}
