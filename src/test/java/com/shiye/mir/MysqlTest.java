package com.shiye.mir;

import com.shiye.mir.dao.mybatis.UserInfoDao;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MysqlTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private RegisterService registerService;

    @Test
    public void testMybatis(){
        UserInfo result = userInfoDao.selectUserInfoByLoginName("test001");
        System.out.println("-------------");
        System.out.println("nickName"+result.getNickName());
        System.out.println("uid"+result.getUserId());
        System.out.println(result);
        System.out.println("-------------");
    }


    @Test
    public void testJdbc(){
        String sql = "select * from userinfo where userId ='test001' ";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        System.out.println(result);
    }

    @Test
    public void selectByEmail(){
        UserInfo a = userInfoDao.selectUserInfoByEmail("q@qq.com");
        System.out.println(a);
    }

    @Test
    public void emailFailedLog(){
        int result = registerService.emailFailedLog("test","email@email.com");
        System.out.println(result);
    }

}
