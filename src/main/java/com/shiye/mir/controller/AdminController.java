package com.shiye.mir.controller;

import com.shiye.mir.annotation.AuthorityCheck;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 首页登录
 * @author fangshaozu
 */
@Slf4j
@Controller
@RequestMapping(value = "/pages",produces = "application/json;charset=UTF-8")
public class AdminController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 首页
     */
    @ResponseBody
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }

    /**
     * 个人中心
     */
    @AuthorityCheck
    @RequestMapping("/self")
    public ModelAndView self(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Object userInfo = request.getSession().getAttribute("userInfo");
        UserInfo uinfo = userInfoService.getUserInfo(userInfo.toString());
        mv.addObject("uinfo",uinfo);
        mv.setViewName("self.html");
        return mv;
    }


}
