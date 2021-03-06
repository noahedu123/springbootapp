package com.cn.enums;

/**
 * 需求审核状态枚举
 */
public enum ApplyAuditStatusEnum implements BasicEnum {
    NOAUDIT(1,"未审核"),
    HASAUDIT(2,"已审核")
    ;
    private String message;
    private Integer code;

    ApplyAuditStatusEnum(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
