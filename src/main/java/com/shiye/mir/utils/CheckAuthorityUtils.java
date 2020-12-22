package com.shiye.mir.utils;

import com.shiye.mir.dao.MysqlDao;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 权限认证工具类
 * @author fangshaozu
 */
@Slf4j
public class CheckAuthorityUtils {

    /**
     * 查询用户余额
     */
    public static boolean checkDeposit(BigDecimal cost, Integer id){
        return cost.compareTo(MysqlDao.getDepositByUid(id).getDeposit())>0;
    }

    /**
     * 邮箱有效性验证
     */
    public static boolean checkEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
