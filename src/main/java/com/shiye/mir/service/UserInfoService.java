package com.shiye.mir.service;

import com.shiye.mir.entity.dto.UserInfo;

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
