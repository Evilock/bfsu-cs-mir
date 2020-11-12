package com.shiye.mir.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping(value = "/pages",produces = "application/json;charset=UTF-8")
public class AdminController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/team")
    public String team() { return "team.html"; }

    @RequestMapping("/intro")
    public String intro() { return "intro.html"; }

    @RequestMapping("/register")
    public String register() { return "register.html"; }

}
