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
     * 用户注册信息
     */
    int insert(UserInfo user);
}
