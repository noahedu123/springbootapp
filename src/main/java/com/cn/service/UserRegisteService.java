package com.cn.service;

import com.cn.enums.UserLoginEnum;

public interface UserRegisteService {
    UserLoginEnum toRegiste(String telephone);
    UserLoginEnum getVerificationCode(String telephone);

}
