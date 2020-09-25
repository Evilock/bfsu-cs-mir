package com.shiye.mir.service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public interface DownloadFileService {
    OutputStream downLoadFile(Long uid, HttpServletResponse response);
}
