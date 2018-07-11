package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.Util.DateUtil;
import com.cn.Util.SmsUtil;
import com.cn.config.AccessKeyConfig;
import com.cn.dao.UserLoginDao;
import com.cn.dataobject.UserLoginTbl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    @Transactional
    public UserLoginEnum toLogin(String username, String password) {
        UserLoginEnum returncode;
        UserLoginTbl userLoginTbl = userLoginDao.findByAccName(username);
        /**
         * 1.未注册用户返回用户未注册信息
         * 2.已经注册用户  -->判断是否锁定（6次）
         *   2.1锁定  --> 判断是否到达解锁时间
         *      2.1.1 解锁时间未到 返回用户被锁定信息
         *      2.1.2 解锁时间到达 重置用户登录次数和锁定时间 更新
         *   密码验证  -->判断密码是否正确
         *     2.2.1 正确 重置锁定时间为0  返回用户成功登录
         *     2.2.2 错误 -->判断是否已经5次
         *       2.2.1 5次 设置锁定时间 返回用户被锁定信息
         *       2.2.2 不到5次 登录次数加1  返回用户密码错误信息
         *   更新
         */
        if (userLoginTbl == null) {      // 1.未注册用户返回用户未注册信息
            return UserLoginEnum.REGISTERED;
        }else{                          //2.已经注册用户
            if(userLoginTbl.getFailureTimes()==6 ){ //锁定
                if(DateUtil.hourMinutes(30).before(userLoginTbl.getLockTime())){ //解锁时间未到
                    return UserLoginEnum.LOCK;
                }
                //解锁时间到达
                userLoginTbl.setFailureTimes(0);
                userLoginTbl.setLockTime(null);
                UserLoginTbl userLoginTbl1 = userLoginDao.save(userLoginTbl);
                if(userLoginTbl1 == null){
                    log.error(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL.getMessage());
                    throw new appException(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL);
                }
            }
            //密码验证
            Date date = new Date();
            if(userLoginTbl.getPassword().equals(password)){ //密码正确
                userLoginTbl.setFailureTimes(0);
                returncode = UserLoginEnum.SUCCESS;
            }else{                                           //密码错误
                if(userLoginTbl.getFailureTimes()==5){
                    userLoginTbl.setLockTime(date);
                    returncode = UserLoginEnum.LOCK;
                }else{
                    returncode = UserLoginEnum.FAIL;
                }
                userLoginTbl.setFailureTimes(userLoginTbl.getFailureTimes()+1);
            }
            UserLoginTbl userLoginTbl1 = userLoginDao.save(userLoginTbl);
            if(userLoginTbl1 == null){
                log.error(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL.getMessage());
                throw new appException(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL);
            }
            return returncode;
        }
    }
}
