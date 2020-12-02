package com.shiye.mir.service.impl;

import com.shiye.mir.dao.mybatis.DepositDao;
import com.shiye.mir.entity.dto.DepositEntity;
import com.shiye.mir.service.DealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 交易实现类
 * @author fangshaozu
 */
@Service
public class DealServiceImpl implements DealService {

    @Resource
    private DepositDao depositDao;

    @Override
    public void depositDecrease(Integer uid, BigDecimal cost) {
        DepositEntity oldValue = depositDao.selectDepositDao(uid);
        depositDao.depositUpdate(uid,oldValue.getDeposit().subtract(cost));
    }
}
