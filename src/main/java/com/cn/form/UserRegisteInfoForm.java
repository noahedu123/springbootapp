package com.cn.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class UserRegisteInfoForm {
    @NotEmpty(message = "手机号不能为空")
    private String telephone;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String registerCity;
}
