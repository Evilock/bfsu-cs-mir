package com.shiye.mir;

import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RegisterTest {

    @Autowired
    private RegisterService registerService;

    @Test
    public void registerTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("33");
        userInfo.setPassword("dssdds");
        userInfo.setEmail("q@qq.com");
        userInfo.setNickName("fuckyou");
        Integer a = registerService.insert(userInfo);
        System.out.println("影响的行数："+a+"行");
    }

}
