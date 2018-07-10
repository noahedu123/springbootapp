package com.cn.enums;

import lombok.Getter;

/**
 * 用户登录状态枚举
 */
@Getter
public enum UserLoginEnum implements BasicEnum{

    FAIL(000,"用户名或密码错误"),
    SUCCESS(001,"登录成功"),
    REGISTERED(002,"用户未注册"),
    LOCK(003,"用户锁定"),
    USER_LOGIN_UPDATE_FAIL(004,"用户登录信息更新失败")
    ;
    private Integer code;

    private String message;

    UserLoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode(){
        return this.code;
    }
}

