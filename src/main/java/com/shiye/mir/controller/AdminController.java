package com.shiye.mir.controller;

import com.shiye.mir.annotation.AuthorityCheck;
import com.shiye.mir.entity.dto.DepositEntity;
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
@RequestMapping(produces = "application/json;charset=UTF-8")
public class AdminController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 首页
     */
    @ResponseBody
    @RequestMapping("/")
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.html");
        return mv;
    }


    /**
     * 首页
     */
    @ResponseBody
    @RequestMapping("/pages/index")
    @AuthorityCheck
    public ModelAndView index(HttpServletRequest request){
        log.info("Index page, uid:{}",request.getSession().getAttribute("userInfo"));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }

    /**
     * 个人中心
     */
    @AuthorityCheck
    @RequestMapping("/pages/self")
    public ModelAndView self(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Object userInfo = request.getSession().getAttribute("userInfo");
        UserInfo uinfo = userInfoService.getUserInfo(userInfo.toString());
        DepositEntity deposit = userInfoService.getDeposit(uinfo.getId());
        mv.addObject("uinfo",uinfo);
        //TODO 用户信息变化后session同步更新
        mv.addObject("deposit",deposit.getDeposit());
        mv.setViewName("self.html");
        return mv;
    }


}
