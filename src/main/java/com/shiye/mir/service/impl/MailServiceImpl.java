package com.shiye.mir.service.impl;

import com.shiye.mir.enums.EnumEmailSendStatus;
import com.shiye.mir.service.IVerifyCodeGen;
import com.shiye.mir.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送实现类
 * @author fangshaozu_sx
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
        log.info("Email Sent.");
    }

    @Override
    public EnumEmailSendStatus sendHtmlEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true,"utf-8");
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            message.setSubject(subject);
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(from));
            messageHelper.setText(content, true);
            mailSender.send(message);
            log.info("Email Sent.");
            return EnumEmailSendStatus.EMAIL_SENT;
        } catch (Exception e) {
            log.error("sendHtmlEmail error", e);
            return EnumEmailSendStatus.EMAIL_FAILED;
        }
    }

    @Override
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(from));
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("Email Sent.");
        } catch (MessagingException e) {
            log.error("sendAttachmentsEmail error", e);
        }

    }

    @Override
    public EnumEmailSendStatus sendEmailVerifyCode(String to) {
        IVerifyCodeGen verifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        String verifyCode = verifyCodeGen.emailVerifyCode(to);
        StringBuilder content = new StringBuilder();
        content.append("<h2>输入下方验证码完成验证（20分钟内有效~）</h2>").append("<h3>").append(verifyCode).append("</h3>");
        String subject = "【Voicat】找回密码~";
        return sendHtmlEmail(to,subject,content.toString());
    }
}
