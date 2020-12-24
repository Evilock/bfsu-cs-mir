package com.shiye.mir.controller;

import com.shiye.mir.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 支付宝API
 * @author fangshaozu
 */
@Slf4j
@Controller
@RequestMapping(value="/alipay",produces = "application/json;charset=UTF-8")
public class AliPayController {

    @Resource(name="alipayService")
    private AlipayService alipayService;

    @RequestMapping("/pay")
    public String pay(@RequestParam("WIDout_trade_no") String id, @RequestParam("WIDsubject") String name, @RequestParam("WIDtotal_amount") Double amount ) throws Exception {
        System.out.println(id);
        System.out.println(name);
        System.out.println(amount);
        BigDecimal pay= BigDecimal.valueOf(amount);
        return alipayService.webPagePay(id, pay, name);
    }

    @RequestMapping("/success")
    public String success(){
        return "pay_success.html";
    }

    @RequestMapping("/test")
    public String testAli(){
        return "testAli.html";
    }
}
