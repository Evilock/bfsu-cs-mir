package com.shiye.mir.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 介绍页
 * @author fangshaozu_sx
 */
@Slf4j
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class InfoPagesController {

    /**
     * 团队介绍页
     */
    @RequestMapping("/team")
    public String team() {
        return "team.html";
    }

    /**
     * 项目介绍页
     */
    @RequestMapping("/intro")
    public String intro() {
        return "intro.html";
    }

    /**
     * 帮助页
     */
    @RequestMapping("/help")
    public String help() {
        return "help.html";
    }
}
