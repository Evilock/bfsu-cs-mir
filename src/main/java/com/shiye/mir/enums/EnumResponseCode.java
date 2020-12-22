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
    EMAIL_EXIST("R00405","邮箱已被注册");

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
