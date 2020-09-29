package com.shiye.mir.dao.mybatis;

import com.shiye.mir.entity.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoDao {
    UserInfo selectPasswordByUid(@Param("userId") String userId);
}
