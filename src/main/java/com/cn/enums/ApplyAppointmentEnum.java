package com.cn.enums;


import lombok.Getter;

/**
 * 需求预约信息枚举
 */
@Getter
public enum ApplyAppointmentEnum implements BasicEnum{
    SUCCESS(4000, "成功")
    ;
    private int code;
    private String message;

    ApplyAppointmentEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
