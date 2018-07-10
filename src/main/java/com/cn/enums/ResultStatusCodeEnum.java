package com.cn.enums;

import lombok.Getter;

/**
 * 异常结果返回信息
 */
@Getter
public enum ResultStatusCodeEnum implements BasicEnum{
    USER_LOGIN_UPDATE_FAIL(2001,"用户登录信息更新失败")
    ;
    private String message;
    private Integer code;
    ResultStatusCodeEnum(Integer code, String message){
        this.message=message;
        this.code=code;
    }
    @Override
    public int getCode(){
        return this.code;
    }
}
