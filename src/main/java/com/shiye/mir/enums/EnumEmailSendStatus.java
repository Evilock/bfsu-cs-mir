package com.shiye.mir.enums;

import lombok.Getter;

@Getter
public enum EnumEmailSendStatus {

    /** 邮件发送成功 */
    EMAIL_SENT(0,"发送成功"),

    /** 邮件发送失败 */
    EMAIL_FAILED(1,"发送失败");

    private int code;

    private String message;

    EnumEmailSendStatus(int code, String message){
        this.code = code;
        this.message = message;
    }
}
