package com.cn.service.Impl;

import com.cn.dao.UserLoginDao;
import com.cn.dataobject.UserLoginTbl;
import com.cn.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;
    @Override
    public String toLogin(String username, String password) {
        UserLoginTbl userLoginTbl=userLoginDao.findByAccName(username);
        if(userLoginTbl==null){

        }
        return "";
    }
}
