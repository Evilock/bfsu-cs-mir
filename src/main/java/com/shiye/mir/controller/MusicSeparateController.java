package com.shiye.mir.controller;

import com.shiye.mir.entity.ApiResponse;
import com.shiye.mir.service.DealService;
import com.shiye.mir.service.SeparatedService;
import com.shiye.mir.utils.WordsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 主功能Controller类
 * @author fangshaozu
 */
@Slf4j
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class MusicSeparateController {

    @Resource
    public SeparatedService separatedService;

    @Resource
    private DealService dealService;

    /**
     * step1: 上传将要被分轨的音频文件，存入缓存
     * @param file 上传的音频文件
     */
    @RequestMapping("/getMusicFile")
    public ApiResponse<Object> getMusicFile(HttpServletRequest request,
                                            @RequestParam("file") MultipartFile file) {
        String userId = request.getSession().getAttribute("userInfo").toString();
        request.getSession().setAttribute("fileName", file.getOriginalFilename());
        log.info("File uploaded, uid:{}",userId);
        return ApiResponse.of("",separatedService.uploadMusic(file,userId));
    }

    /**
     * step2: 下载分轨输出的结果
     */
    @GetMapping(value = "/downloadTracks")
    public String downloadTracks(HttpServletRequest request,
                               HttpServletResponse response) {

        log.info("File downloaded, uid:{}",request.getSession().getAttribute("userInfo"));
        String fileNameInSession = request.getSession().getAttribute("fileName").toString();
        if(fileNameInSession.isEmpty()){
            return "no";
        }
        String fileName = WordsUtils.suffixAbortion(fileNameInSession) + ".zip";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);
        separatedService.downLoadFile(fileName,response);
        return "OK";
    }
}
