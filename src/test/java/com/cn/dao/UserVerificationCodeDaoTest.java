package com.cn.dao;

import com.cn.SpringBootAppApplication;
import com.cn.dataobject.UserVerificationCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVerificationCodeDaoTest {
    @Autowired
    private UserVerificationCodeDao userVerificationCodeDao;
    @Test
    public void findUserVerificationCodeByTelephone() {
        UserVerificationCode userVerificationCode=new UserVerificationCode();
        userVerificationCode.setTelephone("15033801317");
        userVerificationCode.setCode("=81D238");
        UserVerificationCode userVerificationCode1= userVerificationCodeDao.save(userVerificationCode);
        Assert.assertNotNull(userVerificationCode1);
    }

}