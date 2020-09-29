package com.shiye.mir.controller;

import com.shiye.mir.entity.vo.Response;
import com.shiye.mir.service.LoginCheckService;
import com.shiye.mir.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录代码
 */
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class LoginController {

    @Autowired
    private LoginCheckService loginCheckService;

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @ResponseBody
    @RequestMapping(value = "check/{userid}/{password}", method = RequestMethod.POST)
    public Response checkLogin(@PathVariable("userid") String userid,
                               @PathVariable("password") String password,
                               HttpServletRequest request){
        Response response = new Response();
        response.setId(CommonUtils.getUUID());
        if(!StringUtils.isNotEmpty(userid) || !StringUtils.isNotEmpty(password)){
            response.setBody("请输入用户名和密码！");
        }
        if(loginCheckService.checkPassword(userid,password)){
            //密码正确
            response.setBody("userid:"+userid+";password:"+password);
            request.getSession().setAttribute("userInfo", userid + " - " + password);
        }else{
            //密码不正确
            response.setBody("wrong userid or password!");
        }
        return response;
    }



    /**
     * 登出操作
     */
    /*
    @GetMapping(value = "/loginout")
    public String loginout(HttpServletRequest request) {
        String info = "登出操作";
        log.info(info);
        HttpSession session = request.getSession();
        // 将用户信息从session中删除
        session.removeAttribute("userInfo");
        Object userInfo = session.getAttribute("userInfo");
        if (userInfo == null) {
            info = "登出成功";
        } else {
            info = "登出失败";
        }
        log.info(info);
        return info;
    }
    */
}
