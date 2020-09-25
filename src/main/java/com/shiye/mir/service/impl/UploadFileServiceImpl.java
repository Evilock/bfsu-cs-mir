package com.shiye.mir.service.impl;

import com.shiye.mir.enums.EnumErrorStatus;
import com.shiye.mir.enums.EnumSucceedStatus;
import com.shiye.mir.service.UploadFileService;
import com.shiye.mir.utils.MultipartFileToFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public String uploadMusic(MultipartFile file, Long userId) {
        return null;
    }


    /*
    @Autowired
    private RedisDao redisDao;

    @Override
    public String uploadMusic(MultipartFile file,Long userId){
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty() && fileName !=null){
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if(suffixName.equals("mp3") || suffixName.equals("wav")){
                fileName = userId+suffixName;
                //存入缓存中
                try {
                    redisDao.insertIntoRedis(MultipartFileToFileUtils.multipartFileToFile(file),fileName);
                } catch (Exception e) {
                    log.info("upLoadMusicService##Multipart to File FAILED!",e);
                    return EnumErrorStatus.TRANSFER_FAIL.getName();
                }
                return EnumSucceedStatus.SUCCEED.getName();
            }else{ return EnumErrorStatus.WRONG_FORMAT.getName(); }
        } else{ return EnumErrorStatus.EMPTY_FILE.getName(); }
    }

     */
}
