package com.shiye.mir.service;

public interface LoginCheckService {
    /**
     * 密码验证
     */
    Boolean checkPassword(String uid,String password);
}
