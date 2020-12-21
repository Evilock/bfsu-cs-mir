package com.shiye.mir;

import com.shiye.mir.service.MailService;
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
}

