package com.cn.service;

import com.cn.dataobject.UserBl;
import com.cn.enums.UserLoginEnum;

public interface UserRegisteService {
    UserLoginEnum toRegiste(String telephone);
    UserLoginEnum getVerificationCode(String telephone);
    UserLoginEnum verifyCode(String telephone,String code);
    UserLoginEnum saveInfo(UserBl userBl);

}
