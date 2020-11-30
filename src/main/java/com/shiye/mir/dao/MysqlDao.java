package com.shiye.mir.dao;

import com.shiye.mir.dao.mybatis.DepositDao;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * MySQL Dao
 * @author fangshaozu_sx
 */
public class MysqlDao {

    @Resource
    private static DepositDao depositDao;

    public static BigDecimal getDepositByUid(Integer id) {
        return depositDao.selectDepositDao(id);
    }
}
