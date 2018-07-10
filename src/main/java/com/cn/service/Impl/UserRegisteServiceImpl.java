package com.cn.service.Impl;

import com.cn.dao.UserLoginDao;
import com.cn.dataobject.UserLoginTbl;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserRegisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteServiceImpl implements UserRegisteService {

    @Autowired
    private UserLoginDao userLoginDao;

    /**
     * 用户注册——手机号验证
     * @param telephone
     * @return UserLoginEnum
     */
    @Override
    public UserLoginEnum toRegiste(String telephone) {
       UserLoginTbl userLoginTbl= userLoginDao.findByAccName(telephone);
        /**
         * 不存在返回用户未注册，否则返回已注册
         */
       if(userLoginTbl==null){
           return UserLoginEnum.REGISTERED;
       }else{
           return UserLoginEnum.USER_HAS_REGISTER;
       }
    }
}
