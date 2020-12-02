package com.shiye.mir.utils;

import com.shiye.mir.dao.MysqlDao;

import java.math.BigDecimal;

/**
 *
 * @author fangshaozu_sx
 */
public class CheckAuthorityUtils {

    public static boolean checkDeposit(BigDecimal cost, Integer id){
        return cost.compareTo(MysqlDao.getDepositByUid(id).getDeposit())>0;
    }
}
