package com.shiye.mir.enums;

import lombok.Getter;

@Getter
public enum EnumSucceedStatus {

    SUCCEED(1, "传输成功"),

    REDIS_OK(2,"Redis上传成功");

    private Integer code;

    private String name;

    EnumSucceedStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }


    /**
     * 根据编码获取对应的枚举
     */
    public static EnumSucceedStatus toEnum(Integer code) {
        for (EnumSucceedStatus flag : EnumSucceedStatus.values()) {
            if (flag.code.equals(code)) {
                return flag;
            }
        }
        StringBuilder str = new StringBuilder();
        str.append("the argument of ").append(code).append(" have no correspondence  EnumStatus enum!");
        throw new IllegalArgumentException(str.toString());
    }
}
