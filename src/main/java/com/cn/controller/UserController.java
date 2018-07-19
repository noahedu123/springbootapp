package com.cn.controller;

import com.cn.Exception.appException;
import com.cn.Util.ResultUtil;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserLoginService;
import com.cn.service.UserRegisteService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRegisteService userRegisteService;

    //用户登录
    @PostMapping("/login")
    public ResultVo<Object> Login(String username, String password){
        try{
             UserLoginEnum result = userLoginService.toLogin(username,password);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
        }catch (appException e){
            return ResultUtil.GenerateErrorResult(e.getCode(),
                    e.getMessage());
        }
    }
    //用户注册-手机号验证
    @PostMapping("/toRegiste")
    public ResultVo<Object> toRegiste(String telephone){
        try{
            UserLoginEnum result = userRegisteService.toRegiste(telephone);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
        }catch (appException e){
            return ResultUtil.GenerateErrorResult(e.getCode(),
                    e.getMessage());
        }
    }
    //用户注册-发送验证码
    @PostMapping("/getVerificationCode")
    public ResultVo<Object> getVerificationCode(String telephone){
        try{
            UserLoginEnum result = userRegisteService.getVerificationCode(telephone);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
        }catch (appException e){
            return ResultUtil.GenerateErrorResult(e.getCode(),
                    e.getMessage());
        }
    }
    //用户注册-验证码验证
    @PostMapping("/verifyCode")
    public ResultVo<Object> verifyCode(String telephone,String code){
        try{
            UserLoginEnum result = userRegisteService.verifyCode(telephone,code);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
        }catch (appException e){
            return ResultUtil.GenerateErrorResult(e.getCode(),
                    e.getMessage());
        }
    }
}
