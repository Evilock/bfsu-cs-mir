package com.shiye.mir.service;

import com.shiye.mir.entity.dto.DepositEntity;
import com.shiye.mir.entity.dto.UserInfo;

import java.math.BigDecimal;

/**
 * 用户信息获取接口
 * @author fangshaozu
 */
public interface UserInfoService {
    /**
     * 密码验证
     */
    Boolean checkPassword(String uid,String password);

    /**
     * 获取用户全部信息
     */
    UserInfo getUserInfo(String uid);

    /**
     * 获取用户余额
     */
    DepositEntity getDeposit(Integer uid);

    /**
     * 根据邮箱查找
     */
    UserInfo getUserInfoByEmail(String uid);

    /**
     * 修改密码
     */
    int changePassword(String email, String newPassword);
}
