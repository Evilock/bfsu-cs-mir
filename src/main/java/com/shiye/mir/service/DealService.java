package com.shiye.mir.service;

import java.math.BigDecimal;

/**
 * 交易接口类
 * @author fangshaozu
 */
public interface DealService {
    /**
     * 余额减少
     * @param cost 消费
     * @param uid 用户id
     */
    void depositDecrease(String uid, BigDecimal cost);
}
