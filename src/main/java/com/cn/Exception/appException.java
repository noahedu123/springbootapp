package com.cn.Exception;

import com.cn.enums.ResultStatusCodeEnum;
import lombok.Getter;

@Getter
public class appException extends RuntimeException {
    private Integer code;
    public appException(ResultStatusCodeEnum resultStatusCodeEnum){
        super(resultStatusCodeEnum.getMessage());
        this.code = resultStatusCodeEnum.getCode();
    }
    public appException(Integer code, String messsage){
        super(messsage);
        this.code = code;
    }

}
