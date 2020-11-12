package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.RegisterDao;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Override
    public int insert(UserInfo user){
        return registerDao.insert(user);
    }
}
