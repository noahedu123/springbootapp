package com.cn.enums;

import lombok.Getter;

/**
 * 信用卡申请枚举
 */
@Getter
public enum CreditcardEnum implements BasicEnum{

    SUCCESS(3000,"成功"),
    FAIL(3001,"申请失败")
    ;
    private Integer code;

    private String message;

    CreditcardEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode(){
        return this.code;
    }
}

