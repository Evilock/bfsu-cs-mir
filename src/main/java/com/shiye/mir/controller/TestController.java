package com.shiye.mir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fangshaozu
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test.html";
    }
}
