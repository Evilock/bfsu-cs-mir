package com.shiye.mir.service;

import com.shiye.mir.entity.VerifyCode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成接口
 * @author fangshaozu
 */
public interface IVerifyCodeGen {

    /**
     * 生成验证码并返回code，将图片写的os中
     *
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     *
     */
    VerifyCode generate(int width, int height) throws IOException;

    /**
     * 生成邮箱验证码
     */
    String emailVerifyCode(String to);
}
