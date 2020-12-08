package com.shiye.mir.controller;

import com.shiye.mir.common.Constants;
import com.shiye.mir.enums.EnumErrorStatus;
import com.shiye.mir.enums.EnumSucceedStatus;
import com.shiye.mir.service.DealService;
import com.shiye.mir.service.DownloadFileService;
import com.shiye.mir.service.GetSeparatedService;
import com.shiye.mir.service.UploadFileService;
import com.shiye.mir.utils.CheckAuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;


/**
 * 主功能Controller类
 * @author fangshaozu
 */
@Slf4j
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class MusicSeparateController {

    @Resource
    public UploadFileService uploadFileService;

    @Resource
    public DownloadFileService downloadFileService;

    @Resource
    public GetSeparatedService getSeparatedService;

    @Resource
    private DealService dealService;

    /**
     * step1: 上传将要被分轨的音频文件，存入缓存
     * @param file 上传的音频文件
     */
    @ResponseBody
    @PostMapping("/getMusicFile")
    public String getMusicFile(HttpServletRequest request,
                               @RequestParam("file") MultipartFile file) {
        log.info("File uploaded, uid:{}",request.getSession().getAttribute("userInfo"));
        return uploadFileService.uploadMusic(file);
    }

    /**
     * step2: 点击确定后，调用缓存中的音频文件并调用分轨方法，再次存入缓存
     * 若余额不足则提示充值，自动跳转到充值页面
     */
    @RequestMapping(value = "/separate")
    public String getSeparateFile(HttpServletRequest request,
                                  @RequestParam(name = "getBeat", required = false) boolean getBeat,
                                  @RequestParam(name = "getVocal", required = false) boolean getVocal){

        //两个都不选则提示
        if (!getBeat && !getVocal) {return EnumErrorStatus.AT_LEAST_ONE.getName();}
        BigDecimal cost = BigDecimal.ZERO;
        if (getBeat){ cost = cost.add(Constants.BEAT_COST); }
        if (getVocal){ cost = cost.add(Constants.VOCAL_COST); }

        //余额足够的话扣钱并且下载
        Integer userId = (Integer) request.getSession().getAttribute("userInfo");
        if (CheckAuthorityUtils.checkDeposit(cost, userId)){
            dealService.depositDecrease(userId,cost);
            getSeparatedService.doSeparate(userId,getBeat,getVocal);
        }
        return EnumSucceedStatus.SUCCEED.getName();
    }


    /**
     * step3: 下载分轨输出的结果
     */
    @GetMapping(value = "/downloadTracks")
    public String downloadTracks(HttpServletRequest request,
                               HttpServletResponse response) {

        log.info("File downloaded, uid:{}",request.getSession().getAttribute("userInfo"));
        String fileName = "17级附加分.png";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        //文件读取和返回
        try {
            os = response.getOutputStream();
            //文件读取，之后会替换成redis
            bis = new BufferedInputStream(new FileInputStream(new File("F:/TestMIR/"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                //从redis拿出来的数据最后返回给用户
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "OK";
    }
}
