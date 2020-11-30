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
@RequestMapping(value = "/pages",produces = "application/json;charset=UTF-8")
public class InfoPagesController {

    /**
     * 团队介绍页
     */
    @RequestMapping("/team")
    public String team() {
        log.info("Team page opened!");
        return "team.html";
    }

    /**
     * 项目介绍页
     */
    @RequestMapping("/intro")
    public String intro() {
        log.info("Introduction page opened!");
        return "intro.html";
    }

    /**
     * 帮助页
     */
    @RequestMapping("/help")
    public String help() {
        log.info("HELP page opened!");
        return "help.html";
    }
}
