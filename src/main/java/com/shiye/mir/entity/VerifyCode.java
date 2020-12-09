package com.shiye.mir.entity;


import lombok.Data;

/**
 * 验证码实体类
 * @author fangshaozu
 */
@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
