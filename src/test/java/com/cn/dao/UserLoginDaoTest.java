package com.cn.dao;

import com.cn.dataobject.UserLoginTbl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoginDaoTest {
    @Autowired
    private UserLoginDao userLoginDao;
    @Test
    public void findByAccName() {
        UserLoginTbl userLoginTbl=userLoginDao.findByAccName("hyf");
        Assert.assertNotNull(userLoginTbl);
    }
}