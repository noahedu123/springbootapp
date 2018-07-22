package com.cn.enums;

import lombok.Getter;

/**
 * 异常结果返回信息
 */
@Getter
public enum ResultStatusCodeEnum implements BasicEnum{
    USER_LOGIN_UPDATE_FAIL(2001,"用户登录信息更新失败"),
    USER_VERIFICATION_UPDATE_FAIL(2001,"验证码更新失败"),
    USER_SENDMESSAGE_FAIL(2003,"发送信息失败"),
    SCHEDULE_DELETE_VERIFYCODE_FAIL(2004,"定时删除无效验证码失败"),
    SCHEDULE_DELETE_USER_LOGIN_FAIL(2004,"定时删除用户登录信息失败"),
    USERBL_SAVE_FAIL(2005,"用户基本信息保存失败"),
    PARAM_ERROR(2006,"入参错误"),
    UPLOAD_IMAGES_FAIL(2007,"上次头像失败")
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
