package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.UserInfoDao;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fangshaozu
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public Boolean checkPassword(String uid, String password){
        UserInfo userInfo = userInfoDao.selectUserInfoByLoginName(uid);
        if(userInfo!=null){
            return password.equals(userInfo.getPassword());
        }else{
            return false;
        }
    }

    @Override
    public UserInfo getUserInfo(String uid){
        return userInfoDao.selectUserInfoByLoginName(uid);
    }
}
