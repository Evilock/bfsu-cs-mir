package com.shiye.mir.controller;

import com.shiye.mir.entity.VerifyCode;
import com.shiye.mir.entity.vo.Response;
import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.IVerifyCodeGen;
import com.shiye.mir.service.UserInfoService;
import com.shiye.mir.service.impl.SimpleCharVerifyCodeGenImpl;
import com.shiye.mir.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录代码
 * @author fangshaozu
 */
@Slf4j
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class LoginController {

    @Resource
    private UserInfoService loginCheckService;

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }


    @RequestMapping("/register")
    public String register() { return "register.html"; }

    @ResponseBody
    @RequestMapping(value = "/check/{userid}/{password}/{verifyCode}", method = RequestMethod.POST)
    public Response checkLogin(@PathVariable("userid") String userid,
                               @PathVariable("password") String password,
                               @PathVariable("verifyCode") String verifyCode,
                               HttpServletRequest request){
        log.info("inputs are:{},{},{}",userid,password,verifyCode);
        log.info("code in Session:{}",request.getSession().getAttribute("VerifyCode"));
        Response response = new Response();
        response.setId(CommonUtils.getUUID());
        if(verifyCode == null){
            response.setBody(EnumResponseCode.MISS_VERIFY.getId());
        }else if(!verifyCode.equals(request.getSession().getAttribute("VerifyCode").toString())){
            response.setBody(EnumResponseCode.WRONG_VERIFY.getId());
        }else{
            if(loginCheckService.checkPassword(userid,password)){
                response.setBody(EnumResponseCode.SUCCESS.getId());
                request.getSession().setAttribute("userInfo", userid);
                log.info("LOGIN succeed, uid is:{}",userid);
            }else{
                response.setBody(EnumResponseCode.WRONG_INFO.getId());
                log.info("LOGIN failed, uid is:{}",userid);
            }
        }
        return response;
    }

    /**
     * 登出操作
     */
    @ResponseBody
    @RequestMapping(value = "/logout")
    public Response loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        Object userInfo = session.getAttribute("userInfo");
        String info = userInfo == null?EnumResponseCode.LOGOUT_OK.getCode():EnumResponseCode.LOGOUT_FAILED.getCode();
        Response response = new Response();
        response.setBody(info);
        return response;
    }

    /**
     * 获取验证码
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            log.info("Controller verify code generate:{}",code);
            request.getSession().setAttribute("VerifyCode", code);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.info("", e);
        }
    }
}
