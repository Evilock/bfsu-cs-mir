package com.shiye.mir.controller;

import com.shiye.mir.common.Constants;
import com.shiye.mir.enums.EnumErrorStatus;
import com.shiye.mir.enums.EnumSucceedStatus;
import com.shiye.mir.service.DownloadFileService;
import com.shiye.mir.service.GetSeparatedService;
import com.shiye.mir.service.UploadFileService;
import com.shiye.mir.utils.CheckAuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;

@Slf4j
@Controller
@RequestMapping(value = "/pages",produces = "application/json;charset=UTF-8")
public class AdminController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/team")
    public String team() { return "team.html"; }

    @RequestMapping("/intro")
    public String intro() { return "intro.html"; }

}
