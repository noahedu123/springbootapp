package com.cn.controller;

import com.cn.Exception.Exception;
import com.cn.Util.EnumUtil;
import com.cn.Util.ResultUtil;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserLoginService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关操作
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserLoginService userLoginService;

    //用户登录
    @PostMapping("/login")
    public ResultVo<Object> Login(String username, String password){
        try{
             UserLoginEnum result = userLoginService.toLogin(username,password);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
        }catch (Exception e){
            return ResultUtil.GenerateErrorResult(ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL.getCode(),
                    ResultStatusCodeEnum.USER_LOGIN_UPDATE_FAIL.getMessage());
        }
    }

}