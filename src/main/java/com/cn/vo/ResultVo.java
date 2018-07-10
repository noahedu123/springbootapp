package com.cn.vo;

import lombok.Data;

/**
 * 返给前端类
 */
@Data
public class ResultVo<T> {
    //状态码
    private Integer status;
    //信息
    private String msg;
    //具体内容
    private T data;
}
