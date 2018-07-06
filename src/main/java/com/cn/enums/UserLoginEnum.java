package com.cn.enums;

import lombok.Getter;

/**
 * 用户登录状态枚举
 */
@Getter
public enum UserLoginEnum {

    FAIL(0,"用户名或密码错误"),
    SUCCESS(1,"登录成功"),
    REGISTERED(2,"用户未注册"),
    LOCK(3,"用户锁定")
    ;
    private Integer code;

    private String message;

    UserLoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

