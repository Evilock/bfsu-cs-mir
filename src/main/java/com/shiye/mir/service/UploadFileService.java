package com.shiye.mir.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String uploadMusic(MultipartFile file);
}
