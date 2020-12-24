package com.shiye.mir.enums;

import lombok.Getter;


/**
 * 返回码
 * @author fangshaozu
 */

@Getter
public enum EnumResponseCode implements IEnum<String>{
    /** 成功 */
    SUCCESS("A00000", "登录成功！"),
    /** 错误验证码 */
    WRONG_VERIFY("E00204","请输入验证码！"),
    /** 无验证码 */
    MISS_VERIFY("E00401","验证码错误！"),
    /** 用户名密码错误 */
    WRONG_INFO("E00400","用户名密码错误"),
    /** 注销成功 */
    LOGOUT_OK("A00001","注销成功"),
    /** 注销失败 */
    LOGOUT_FAILED("F00002","注销失败"),

    /** 注册成功 */
    REGISTER_SUCCESS("R00200","注册成功，请前往邮箱认证您的信息！"),
    /** 注册失败 */
    REGISTER_FAILED("R00400","注册失败"),
    /** 注册失败 */
    USER_EXIST("R00403","用户存在"),
    /** 邮箱错误 */
    EMAIL_FAILED("R00401","邮箱格式不正确"),
    /** 邮箱已被注册 */
    EMAIL_EXIST("R00405","邮箱已被注册"),

    PASSWORD_DIF("P00400","两次输入不一致！"),
    WRONG_VERIFY_2("P00401","验证码错误"),
    INPUT_CHECK("P00402","请检查输入"),


    FILE_UP_SUCCESS("F00200","文件上传成功"),
    FILE_UP_FAILED("F00400","文件上传失败"),
    FILE_UP_TOO_BIG("F00401","上传的文件太大"),

    FILE_DOWN_SUCCESS("F00200","文件下载成功"),
    FILE_DOWN_FAILED("F00400","文件下载失败"),

    FILE_SEPARATE_SUCCESS("S00200","模型转换成功"),
    FILE_SEPARATE_FAILED("S00400","模型转换失败"),
    FILE_COMPRESS_SUCCESS("C00200","文件压缩成功"),
    FILE_COMPRESS_FAILED("C00400","文件压缩失败"),
    UNKNOWN_ERROR("C00000","未知错误");

    private String code;

    private String message;

    @Override
    public String getId() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    EnumResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
