package com.shiye.mir.entity.dto;

import lombok.Data;

/**
 * 用户信息实体类
 * @author fangshaozu_sx
 */
@Data
public class UserInfo {

    private String id;

    private String userId;

    private String password;

    private String email;

    private String nickName;
}
