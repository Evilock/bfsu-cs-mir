package com.shiye.mir.dao.mybatis;

import com.shiye.mir.entity.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public interface RegisterDao {
    /**
     * 用户注册信息
     */
    int insert(UserInfo user);
}
