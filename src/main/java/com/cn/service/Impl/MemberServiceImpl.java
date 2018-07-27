package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.Util.DateUtil;
import com.cn.dao.UserBlDao;
import com.cn.dataobject.UserBl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 飞虎队专享会员
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    UserBlDao userBlDao;

    /**
     * 修改会员信息
     * @param telephone
     * @return
     */
    @Override
    public UserLoginEnum updateMember(String telephone) {
        /**
         * 1.查询用户信息  -->
         *      1.1 不存在 返回用户未注册
         *      1.2 设置会员信息 开始时间结束时间
         *  2.保存 返回成功，失败抛出异常
         */
        UserBl userBl = userBlDao.findUserBlByTelephone(telephone);
        if(userBl == null){
           return  UserLoginEnum.REGISTERED;
        }
        userBl.setMembership(2);//1 不是会员 2 是会员
        userBl.setMembershipStart(new Date());  //会员开始时间
        userBl.setMembershipEnd(DateUtil.getDateAfterYear(1)); //会员结束时间
        UserBl userBl1 = userBlDao.save(userBl);
        if(userBl1 == null){
            throw new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
        }
        return UserLoginEnum.SUCCESS;
    }
}
