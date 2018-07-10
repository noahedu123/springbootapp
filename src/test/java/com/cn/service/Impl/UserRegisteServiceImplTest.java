package com.cn.service.Impl;

import com.cn.dataobject.UserLoginTbl;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserRegisteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRegisteServiceImplTest {

    @Autowired
    private UserRegisteService userRegisteService;
    @Test
    public void toRegiste() {
        UserLoginEnum userLoginTbl=userRegisteService.toRegiste("15010979106");
        Assert.assertEquals(userLoginTbl.getMessage(),"用户未注册");
    }
}