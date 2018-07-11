package com.cn.enums;

import lombok.Getter;

/**
 * 用户登录状态枚举
 */
@Getter
public enum UserLoginEnum implements BasicEnum{

    SUCCESS(000,"成功"),
    FAIL(001,"用户名或密码错误"),
    REGISTERED(002,"用户未注册"),
    LOCK(003,"用户锁定"),
    USER_LOGIN_UPDATE_FAIL(004,"用户登录信息更新失败"),
    USER_HAS_REGISTER(005,"用户已注册"),
    USER_SENDMESSAGE_FAIL(006,"验证码发送失败")


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

