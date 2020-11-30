package com.shiye.mir.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 余额实体类
 * @author fangshaozu_sx
 */
@Data
public class DepositEntity {

    private Integer id;

    private BigDecimal deposit;
}
