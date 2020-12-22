package com.shiye.mir.service;

import com.shiye.mir.enums.EnumEmailSendStatus;

/**
 * 邮件发送服务接口
 * @author fangshaozu_sx
 */
public interface MailService {

    /**
     * 发送普通邮件
     * @param to 发送人
     * @param subject 收件人
     * @param content 内容
     */
    void sendSimpleEmail(String to, String subject, String content);

    /**
     * HTML类型邮件
     * @param to 发送方
     * @param subject 接收方
     * @param content 内容
     * @return 发送状态返回
     */
    EnumEmailSendStatus sendHtmlEmail(String to, String subject, String content);

    /**
     * 带附件的邮件
     * @param to 发送方
     * @param subject 接收方
     * @param content 内容
     * @param filePath 文件路径
     */
    void sendAttachmentsEmail(String to,String subject, String content, String filePath);
}
