package com.cn.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ApplyAppointmentForm {
    /**
     *用户id
     */
    private Integer userId;
    /**
     *姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;
    /**
     *身份证号码
     */
    @NotEmpty(message = "身份证号不能为空")
    private String idcard;
    /**
     *手机号
     */
    private String phone;
    /**
     *微信号
     */
    private String wechat;
    /**
     *公司名称
     */
    private String companyName;
    /**
     *申请金额
     */
    private double applyMoney;
    /**
     *提交类型
     */
    private String type;
    /**
     *需求详情
     */
    private String detail;

}
