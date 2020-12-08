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
    LOGOUT_FAILED("F00002","注销失败");

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
