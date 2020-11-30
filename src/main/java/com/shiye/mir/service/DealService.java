package com.shiye.mir.service;

import java.math.BigDecimal;

/**
 * 交易接口类
 * @author fangshaozu
 */
public interface DealService {
    /**
     * 余额减少
     */
    void depositDecrease(Integer uid, BigDecimal cost);
}
