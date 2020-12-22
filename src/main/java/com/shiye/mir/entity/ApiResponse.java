package com.shiye.mir.entity;

import com.shiye.mir.enums.EnumResponseCode;
import lombok.Data;

/**
 * Api响应结构体
 * @author fangshaozu_sx
 */
@Data
public class ApiResponse<E> {
    /** 接口返回码 */
    private String code;
    
    /** 接口返回信息 */
    private String message;
    
    /** 请求ID */
    private String messageId;
    
    /** 响应内容 */
    private E data;
    
    private ApiResponse(String code, String message, String messageId, E data) {
        this.code = code;
        this.message = message;
        this.messageId = messageId;
        this.data = data;
    }

    public static <E> ApiResponse<E> of(String messageId, EnumResponseCode apiCode) {
        return new ApiResponse<E>(apiCode.getCode(), apiCode.getMessage(), messageId, null);
    }

    public static <E> ApiResponse<E> of(String messageId, EnumResponseCode apiCode, E data) {
        return new ApiResponse<E>(apiCode.getCode(), apiCode.getMessage(), messageId, data);
    }

    public static <E> ApiResponse<E> of(String messageId, EnumResponseCode apiCode, String message) {
        return new ApiResponse<E>(apiCode.getCode(), message, messageId, null);
    }

    public static <E> ApiResponse<E> of(String messageId, EnumResponseCode apiCode, String message, E data) {
        return new ApiResponse<E>(apiCode.getCode(), message, messageId, data);
    }
}
