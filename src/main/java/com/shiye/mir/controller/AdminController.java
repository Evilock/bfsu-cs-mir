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

    @Autowired
    public UploadFileService uploadFileService;

    @Autowired
    public DownloadFileService downloadFileService;

    @Autowired
    public GetSeparatedService getSeparatedService;

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    /**
     * step1: 上传将要被分轨的音频文件，存入缓存
     * @param file 上传的音频文件
     */
    @PostMapping("/getMusicFile")
    @ResponseBody
    public String getMusicFile(@RequestParam("file") MultipartFile file) {
        return uploadFileService.uploadMusic(file);
    }

    /**
     * step2: 点击确定后，调用缓存中的音频文件并调用分轨方法，再次存入缓存
     * 若余额不足则提示充值，自动跳转到充值页面
     */
    @RequestMapping(value = "/separate")
    public String getSeparateFile(@RequestParam(name = "userId", required = false) Long userId,
                                  @RequestParam(name = "getBeat", required = false) boolean getBeat,
                                  @RequestParam(name = "getVocal", required = false) boolean getVocal){

        //两个都不选则提示
        if (!getBeat && !getVocal) return EnumErrorStatus.AT_LEAST_ONE.getName();

        BigDecimal cost = BigDecimal.ZERO;
        if (getBeat){ cost.add(Constants.BEAT_COST); }
        if (getVocal){ cost.add(Constants.VOCAL_COST); }

        if (CheckAuthorityUtils.checkDeposit(cost, userId)){
            getSeparatedService.doSeparate(userId,getBeat,getVocal);
        }
        return EnumSucceedStatus.SUCCEED.getName();
    }


    /**
     * step3: 下载分轨输出的结果
     */
    @RequestMapping(value = "/downloadTracks")
    public Object downloadTracks(@RequestParam(name = "userId", required = false) Long userId,
                                 HttpServletResponse response) {
        OutputStream os = downloadFileService.downLoadFile(userId, response);
        return "";
    }
}
