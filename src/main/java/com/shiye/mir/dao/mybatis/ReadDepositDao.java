package com.shiye.mir.dao.mybatis;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface ReadDepositDao {

    /**
     * 查出用户余额
     */
    BigDecimal selectDepositDao(Long uid);
}
