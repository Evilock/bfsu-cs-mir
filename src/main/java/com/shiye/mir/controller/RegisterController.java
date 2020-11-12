package com.shiye.mir.controller;

import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user",produces = "application/json;charset=UTF-8")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/register")
    @ResponseBody
    public String register(UserInfo user){
        Integer linesBeenAffected = registerService.insert(user);
        if(linesBeenAffected>0){
            return "注册成功，请登录！";
        }
        return "注册失败，请重试！";
    }
}
