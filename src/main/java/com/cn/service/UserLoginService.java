package com.cn.service;

import com.cn.enums.UserLoginEnum;

public interface UserLoginService {
    UserLoginEnum toLogin(String username, String password);
    UserLoginEnum getVerificationCode(String telephone);

}
