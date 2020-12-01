package com.shiye.mir.service;

import com.shiye.mir.entity.dto.UserInfo;

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
}
