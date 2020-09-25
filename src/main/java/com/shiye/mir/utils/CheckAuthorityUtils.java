package com.shiye.mir.utils;

import com.shiye.mir.dao.MysqlDao;

import java.math.BigDecimal;

public class CheckAuthorityUtils {

    public static boolean checkDeposit(BigDecimal cost, Long uid){
        return cost.compareTo(MysqlDao.getDepositByUid(uid)) == 1;
    }
}
