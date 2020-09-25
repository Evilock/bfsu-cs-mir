package com.shiye.mir.enums;

import lombok.Getter;

@Getter
public enum EnumErrorStatus {

    WRONG_FORMAT(1,"不支持的格式"),

    EMPTY_FILE(2, "空文件"),

    TRANSFER_FAIL(3,"文件传输失败"),

    AT_LEAST_ONE(4,"至少选择一个");

    private Integer code;

    private String name;

    EnumErrorStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }


    /**
     * 根据编码获取对应的枚举
     */
    public static EnumErrorStatus toEnum(Integer code) {
        for (EnumErrorStatus flag : EnumErrorStatus.values()) {
            if (flag.code.equals(code)) {
                return flag;
            }
        }
        StringBuilder str = new StringBuilder();
        str.append("the argument of ").append(code).append(" have no correspondence  EnumStatus enum!");
        throw new IllegalArgumentException(str.toString());
    }


}