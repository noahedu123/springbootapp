package com.cn.service.Impl;

import com.cn.enums.UserLoginEnum;
import com.cn.service.UserLoginService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserLoginServiceImplTest {
    @Autowired
    private UserLoginService userLoginService;
    @Test
    public void toLogin() {
//        String password="1273";
//        String username="hyf";
//        UserLoginEnum userLoginEnum=userLoginService.toLogin(username,password);
//        Assert.assertEquals(userLoginEnum.getMessage(),UserLoginEnum.FAIL.getMessage());
    }
}