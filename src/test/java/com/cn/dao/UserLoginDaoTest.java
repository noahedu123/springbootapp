package com.cn.dao;

import com.cn.dataobject.UserLoginTbl;
import com.cn.enums.UserLoginEnum;
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
        UserLoginTbl userLoginTbl=userLoginDao.findByAccName("aa");
       // UserLoginTbl userLoginTbl = new UserLoginTbl();
        userLoginTbl.setAccName("sun");
        userLoginTbl.setPassword("1235");
        UserLoginTbl result  = userLoginDao.save(userLoginTbl);
        Assert.assertNotNull(result);
    }
}