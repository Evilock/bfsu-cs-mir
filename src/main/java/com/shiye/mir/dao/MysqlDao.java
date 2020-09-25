package com.shiye.mir.dao;

import com.shiye.mir.dao.mybatis.ReadDepositDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class MysqlDao {

    @Autowired
    private static ReadDepositDao readDepositDao;

    public static BigDecimal getDepositByUid(Long uid) {
        return readDepositDao.selectDepositDao(uid);
    }
}
