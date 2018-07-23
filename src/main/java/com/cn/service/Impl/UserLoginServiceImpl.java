package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.Util.DateUtil;
import com.cn.dao.UserBlDao;
import com.cn.dao.UserLoginDao;
import com.cn.dataobject.UserBl;
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
    @Autowired
    private UserBlDao userBlDao;

    /**
     * 用户登录验证
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public UserLoginEnum toLogin(String username, String password) {
        /**
         * 查询userbl表，-->判断是否存在
         *  1.不存在 返回用户未注册信息
         *  2.存在  -->判断用户密码是否正确
         *      2.1.正确 调用删除登录信息方法
         *      2.2.错误  调用增加登录信息方法
         */
        UserLoginEnum returncode;
        UserBl userBl = userBlDao.findUserBlByTelephone(username);
        if(userBl == null){ //不存在 返回用户未注册信息
            return UserLoginEnum.REGISTERED;
        }
        //用户已注册 判断密码
        if(userBl.getPassword().equals(password)){ //密码正确 调用删除登录错误信息方法
            returncode = toDeleteLoginInfo(username);
        }else{
            returncode = toAddLoginInfo(username);
        }
        return returncode;
    }

    /**
     * 判断是否存在密码错误记录
     *   1.不存在 用户失败次数1返回失败信息
     *   2.存在  判断是否锁定
     *     2.1 锁定 -->判断是否到达解锁时间
     *          2.1.1 到达 --> 锁定时间清零 次数1
     *          2.1.2 未达到 --> 返回锁定信息
     *     2.2 未锁定 判断是否已经五次
     *         2.2.1 已经5次 设置用户锁定次数6和锁定时间 返回锁定信息
     *         2.2.2 未到5次 设置用户锁定次数+1 返回用户错误信息
     *   3.更新
     * @param telephone
     * @return
     */
    private UserLoginEnum toAddLoginInfo(String telephone){
        UserLoginEnum returnInfo;
        UserLoginTbl userLoginTbl = userLoginDao.findByAccName(telephone);
        UserLoginTbl result;
        if(userLoginTbl == null){ //不存在 用户无登录错误信息
            UserLoginTbl userLoginTbl2 = new UserLoginTbl();
            userLoginTbl2.setAccName(telephone);
            userLoginTbl2.setFailureTimes(1);
            returnInfo = UserLoginEnum.FAIL;
            result = userLoginDao.save(userLoginTbl2);
        }else{ //存在 用户登录错误信息
            if(userLoginTbl.getFailureTimes()==6){ //6次
                if(DateUtil.hourMinutes(30).after(userLoginTbl.getLockTime())){//到达解锁时间
                    userLoginTbl.setFailureTimes(1);
                    userLoginTbl.setFailureTimes(null);
                    returnInfo = UserLoginEnum.FAIL;
                }else{ //没有到达解锁时间
                    return UserLoginEnum.LOCK;
                }
            }else if(userLoginTbl.getFailureTimes()==5){ //5次 设置用户锁定次数6和锁定时间 返回锁定信息
                Date date = new Date();
                userLoginTbl.setFailureTimes(6);
                userLoginTbl.setLockTime(date);
                returnInfo = UserLoginEnum.LOCK;
            }else { //1 2 3 4 次
                userLoginTbl.setFailureTimes(userLoginTbl.getFailureTimes()+1);
                returnInfo = UserLoginEnum.FAIL;
            }
            result = userLoginDao.save(userLoginTbl);
        }
        if(result == null){ //判断操作数据库是否正常
            log.error(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL.getMessage());
            throw new appException(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL);
        }

        return returnInfo;
    }

    /**
     * 1.如果不存在密码错误记录 返回成功
     * 2.否则判断是否到达解锁时间
     *   2.1 未到达 返回用户锁定信息
     *   2.2 到达  删除用户登录信息
     * @param telephone
     * @return
     */
    private UserLoginEnum toDeleteLoginInfo(String telephone){
        UserLoginTbl userLoginTbl = userLoginDao.findByAccName(telephone);
        if(userLoginTbl == null){ //如果不存在密码错误记录 返回成功
            return UserLoginEnum.SUCCESS;
        }
        if(DateUtil.hourMinutes(30).before(userLoginTbl.getLockTime())){ //未到达解锁时间 返回用户锁定信息
            return  UserLoginEnum.LOCK;
        }else{
            userLoginDao.delete(userLoginTbl);
            return UserLoginEnum.SUCCESS;
        }
    }

}
