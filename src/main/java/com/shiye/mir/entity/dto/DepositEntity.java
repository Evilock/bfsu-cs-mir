package com.shiye.mir.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 余额实体类
 * @author fangshaozu
 */
@Data
public class DepositEntity {

    /** 用户ID */
    private String id;

    /** 余额 */
    private BigDecimal deposit;
}
