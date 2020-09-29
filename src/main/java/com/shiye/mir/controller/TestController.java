package com.shiye.mir.controller;

import com.shiye.mir.entity.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping({"/", ""})
    public Response test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //return modelAndView;
        return new Response("11","fuck");
    }

    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res) {
        String fileName = "17级附加分.png";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("F:/TestMIR/"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
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
        System.out.println("success");
    }
}
