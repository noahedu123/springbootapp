package com.cn.enums;

import lombok.Getter;

/**
 * 用户登录状态枚举
 */
@Getter
public enum UserLoginEnum implements BasicEnum{

    SUCCESS(1000,"成功"),
    FAIL(1001,"用户名或密码错误"),
    REGISTERED(1002,"用户未注册"),
    LOCK(1003,"用户锁定"),
    USER_HAS_REGISTER(1005,"用户已注册"),
    USER_SENDMESSAGE_FAIL(1006,"发送信息失败")


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

