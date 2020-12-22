package com.shiye.mir.dao.mybatis;

import com.shiye.mir.entity.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户信息Dao
 * @author fangshaozu
 */
@Mapper
@Repository
public interface UserInfoDao {
    /**
     * 获得uid对应userInfo
     * @param userId 用户ID
     * @return 用户完整信息
     */
    UserInfo selectUserInfoByLoginName(@Param("userId") String userId);

    /**
     * 用户注册
     * @param user 用户
     * @return 被影响的行数
     */
    int insert(UserInfo user);

    /**
     * 激活
     * @param userId 用户ID
     * @return 被影响行数
     */
    int activate(String userId);

    /**
     * 根据邮箱获得用户
     * @param email 邮箱
     * @return 用户信息
     */
    UserInfo selectUserInfoByEmail(String email);

    /**
     * 打印邮件失败日志
     * @param userId userId
     * @param email 邮箱
     * @param statement 场景
     * @return 被影响行数
     */
    int emailFailedLog(String userId, String email, String statement);

    /**
     * 改密码
     * @param email 邮箱
     * @param password 密码
     * @return 影响的行数
     */
    int changePassword(String email,String password);
}
