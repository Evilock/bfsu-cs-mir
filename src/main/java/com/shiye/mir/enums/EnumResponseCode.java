package com.shiye.mir.enums;

import lombok.Getter;


/**
 * 返回码
 * @author fangshaozu_sx
 */

@Getter
public enum EnumResponseCode implements IEnum<String>{
    /** 成功 */
    SUCCESS("A00000", "成功"),
    /** 无数据 */
    NO_DATA("A00204","无数据"),
    /** 操作失败 */
    ERROR("E00400","操作失败"),
    /** 系统错误 */
    SYSTEM_ERROR("E00400","系统错误"),
    /** 参数错误 */
    INVALID_PARAM("E00400", "参数错误"),
    /** 签名错误 */
    INVALID_SIGN("E00401", "签名错误");


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
