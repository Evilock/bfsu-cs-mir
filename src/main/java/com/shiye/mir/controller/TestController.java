package com.shiye.mir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hi")
public class TestController {

    @RequestMapping({"/test", ""})
    public String test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //return modelAndView;
        return "fuck";
    }
}
