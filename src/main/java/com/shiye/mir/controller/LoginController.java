package com.shiye.mir.controller;

import com.shiye.mir.entity.vo.Response;
import com.shiye.mir.service.UserInfoService;
import com.shiye.mir.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping("/pages/login")
    public String login(){
        return "login.html";
    }


    @RequestMapping("/pages/register")
    public String register() { return "register.html"; }

    @ResponseBody
    @RequestMapping(value = "/pages/check/{userid}/{password}", method = RequestMethod.POST)
    public Response checkLogin(@PathVariable("userid") String userid,
                               @PathVariable("password") String password,
                               HttpServletRequest request){
        Response response = new Response();
        response.setId(CommonUtils.getUUID());
        if(!StringUtils.isNotEmpty(userid) || !StringUtils.isNotEmpty(password)){
            response.setBody("请输入用户名和密码！");
            log.info("Username | password miss!");
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
}
