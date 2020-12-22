package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.UserInfoDao;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.enums.EnumEmailSendStatus;
import com.shiye.mir.service.MailService;
import com.shiye.mir.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户注册服务类
 * @author fangshaozu_sx
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private MailService mailService;

    @Resource
    private UserInfoDao userinfoDao;

    @Override
    public int insert(UserInfo user){
        return userinfoDao.insert(user);
    }



    @Override
    public EnumEmailSendStatus sendVerifyMail(String to, String userId) {
        String subject = "【Voicat】注册验证码";
        StringBuilder content = new StringBuilder();
        String url = "http://www.voicat.com/register/verify?id="+"userId";
        content.append("<h2>点击下方超链接进行验证<h2>");
        content.append("<br><h3><a href = \"").append(url).append("\">").append(url).append("</a>").append("</h3>");
        content.append("<br><h3>感谢使用VOICAT~</h3>");

        return mailService.sendHtmlEmail(to,subject,content.toString());
    }

    @Override
    public int activate(String userId) {
        return userinfoDao.activate(userId);
    }

    @Override
    public int emailFailedLog(String userId, String email) {
        return userinfoDao.emailFailedLog(userId,email);
    }
}
