package com.shiye.mir.controller;

import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
@RequestMapping(value = "/pages",produces = "application/json;charset=UTF-8")
public class AdminController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 首页
     */
    @ResponseBody
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        //TODO 直接展示首页，当前必须登录才能进入
        return jumpStatus(request,"index.html");
    }

    /**
     * 检查状态并留在本页
     */
    public ModelAndView commonStatus(HttpServletRequest request,String url){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(url);
        if(request.getSession().getAttribute("userInfo")!=null){
            Object userInfo = request.getSession().getAttribute("userInfo");
            UserInfo uinfo = userInfoService.getUserInfo(userInfo.toString());
            mv.addObject("uinfo",uinfo);
        }
        System.out.println(mv.toString());
        return mv;
    }

    /**
     * 个人中心
     */
    @RequestMapping("/self")
    public ModelAndView self(HttpServletRequest request) {
        return jumpStatus(request,"self.html");
    }

    /**
     * 检查状态并跳转到login界面
     */
    public ModelAndView jumpStatus(HttpServletRequest request,String url){
        ModelAndView mv = new ModelAndView();
        if(request.getSession().getAttribute("userInfo")==null){
            mv.setViewName("login.html");
        }else{
            Object userInfo = request.getSession().getAttribute("userInfo");
            UserInfo uinfo = userInfoService.getUserInfo(userInfo.toString());
            mv.setViewName(url);
            mv.addObject("uinfo",uinfo);
        }
        return mv;
    }

}
