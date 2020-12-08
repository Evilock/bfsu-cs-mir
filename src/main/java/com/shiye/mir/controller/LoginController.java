package com.shiye.mir.controller;

import com.shiye.mir.entity.VerifyCode;
import com.shiye.mir.entity.vo.Response;
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
            response.setBody("请输入验证码");
            return response;
        }
        if(!verifyCode.equals(request.getSession().getAttribute("VerifyCode").toString())){
            response.setBody("验证码错误");
            return response;
        }
        if(loginCheckService.checkPassword(userid,password)){
            response.setBody("userid:"+userid+";password:"+password);
            request.getSession().setAttribute("userInfo", userid);
            log.info("LOGIN succeed, uid is:{}",userid);
        }else{
            response.setBody("wrong userid or password!");
            log.info("LOGIN failed, uid is:{}",userid);
        }
        return response;
    }

    /**
     * 登出操作
     */
    @GetMapping(value = "/logout")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("Log out, session is:{}",session.getAttribute("userInfo"));
        // 将用户信息从session中删除
        session.removeAttribute("userInfo");
        Object userInfo = session.getAttribute("userInfo");
        String info = userInfo==null?"Logout Done!":"Logout Failed!";
        log.info(info);
        return info;
    }

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
