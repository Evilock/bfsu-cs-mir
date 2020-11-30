package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.UserInfoDao;
import com.shiye.mir.entity.dto.UserInfo;
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
    private UserInfoDao userinfoDao;

    @Override
    public int insert(UserInfo user){
        return userinfoDao.insert(user);
    }
}
