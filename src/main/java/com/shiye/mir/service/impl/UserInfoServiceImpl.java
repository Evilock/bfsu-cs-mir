package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.UserInfoDao;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public Boolean checkPassword(String uid,String password){
        UserInfo userInfo = userInfoDao.selectPasswordByUid(uid);
        if(userInfo!=null){
            return password.equals(userInfo.getPassword());
        }else{
            return false;
        }
    }

    public UserInfo getUserInfo(String uid){
        return userInfoDao.selectPasswordByUid(uid);
    }
}
