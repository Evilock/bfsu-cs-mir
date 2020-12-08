package com.shiye.mir.entity;


import lombok.Data;

/**
 * @author fangshaozu_sx
 */
@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
