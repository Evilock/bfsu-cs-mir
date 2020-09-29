package com.shiye.mir.service.impl;

import com.shiye.mir.service.DownloadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Service
public class DownloadFileServiceImpl implements DownloadFileService {
    /*
    @Autowired
    private RedisDao redisDao;

    */
    @Override
    public OutputStream downLoadFile( Long uid, HttpServletResponse response){
        return null;
    }

    public void closeInputStream(InputStream in){
        if (in != null) {
            try { in.close(); } catch (IOException e) {
                log.info("IOException##Close Connection", e);
            }
        }
    }
}
