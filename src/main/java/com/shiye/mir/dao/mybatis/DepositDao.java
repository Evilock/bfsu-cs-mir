package com.shiye.mir.dao.mybatis;

import com.shiye.mir.entity.dto.DepositEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 用户余额查询Dao
 * @author fangshaozu_sx
 */
@Component
public interface DepositDao {

    /**
     * 查出用户余额
     * @param id 用户id
     * @return 余额
     */
    DepositEntity selectDepositDao(@Param("id") Integer id);


    /**
     * 用户余额变动
     * @param id 用户id
     * @param newValue 新的余额
     */
    void depositUpdate(@Param("id") Integer id, BigDecimal newValue);
}
