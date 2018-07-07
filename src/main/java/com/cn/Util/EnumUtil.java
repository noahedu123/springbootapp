package com.cn.Util;

import com.cn.enums.BasicEnum;

public class EnumUtil {
    public static <T extends BasicEnum> T getByCode(Integer code,Class<T> enumclass){
        for(T each: enumclass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
