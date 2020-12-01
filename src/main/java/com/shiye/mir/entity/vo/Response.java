package com.shiye.mir.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fangshaozu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response{

    private String id;

    private String body;

    /**
     * 返回信息

     */
    public static  Response of(String id, String body) {
        return new Response(id, null);
    }
}