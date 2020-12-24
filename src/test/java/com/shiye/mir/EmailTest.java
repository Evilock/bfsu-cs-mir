package com.shiye.mir;

import com.shiye.mir.service.MailService;
import com.shiye.mir.utils.CommonUtils;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Resource
    private MailService mailService;

    @Test
    public void sendmail() {
        mailService.sendSimpleEmail("evilockfang0920@163.com","主题：你好普通邮件","内容：第一封邮件");
    }
    @Test
    public void sendmailHtml(){
        mailService.sendHtmlEmail("evilockfang0920@163.com","主题：你好html邮件","<h1>内容：第一封html邮件</h1>");
    }

    @Test
    public void utilTest(){
        System.out.println(CommonUtils.checkEmail("8937546464@qq.com"));
        System.out.println(CommonUtils.checkEmail("email@qq.com"));
        System.out.println(CommonUtils.checkEmail("8937546464·qq.com"));
        System.out.println(CommonUtils.checkEmail("89375.46464@qq.com"));
    }
    @Test
    public void printA(){
        StringBuilder content = new StringBuilder();
        String url = "http://www.voicat.com/register/verify?id="+"userId";
        content.append("<h2>点击下方超链接进行验证<h2>");
        content.append("<br><h3><a href = \"").append(url).append("\">").append(url).append("</a>").append("</h3>");
        content.append("<br><h3>感谢使用VOICAT~</h3>");

        System.out.println(content.toString());
    }
}

