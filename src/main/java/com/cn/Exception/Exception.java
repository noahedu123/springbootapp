package com.cn.Exception;

import com.cn.enums.ResultStatusCodeEnum;
import lombok.Getter;

@Getter
public class Exception extends RuntimeException {
    private Integer code;
    public Exception(ResultStatusCodeEnum resultStatusCodeEnum){
        super(resultStatusCodeEnum.getMessage());
        this.code = resultStatusCodeEnum.getCode();
    }
    public Exception(Integer code,String messsage){
        super(messsage);
        this.code = code;
    }

}
