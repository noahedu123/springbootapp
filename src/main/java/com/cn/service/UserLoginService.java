package com.cn.service;

import com.cn.enums.UserLoginEnum;
import com.cn.config.AccessKeyConfig;

public interface UserLoginService {
    UserLoginEnum toLogin(String username, String password);

}
