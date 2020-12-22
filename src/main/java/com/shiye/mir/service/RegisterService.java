package com.shiye.mir.service;

import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.enums.EnumEmailSendStatus;

/**
 * 注册类接口
 */
public interface RegisterService {
    /**
     * 插入一个新用户
     * @param user 用户类
     * @return 被影响的行数
     */
    int insert(UserInfo user);

    /**
     * 发送确认邮件
     * @param to 收件方
     * @param userId 用户ID
     * @return 发送状态
     */
    EnumEmailSendStatus sendVerifyMail(String to, String userId);

    /**
     * 验证邮箱完毕，激活用户
     * @param userId 用户ID
     * @return 被影响行数
     */
    int activate(String userId);

    /**
     * 打印邮件发送失败日志
     * @param userId 用户ID
     * @param email 邮件
     * @param statement 场景
     * @return 返回值
     */
    int emailFailedLog(String userId, String email, String statement);

}
