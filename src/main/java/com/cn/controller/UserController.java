package com.cn.controller;

import com.cn.Exception.appException;
import com.cn.Util.ResultUtil;
import com.cn.dataobject.UserBl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.form.UserRegisteInfoForm;
import com.cn.service.UserLoginService;
import com.cn.service.UserRegisteService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResultVo<Object> Login(@RequestParam("username") String username,
                                  @RequestParam("password") String password){
             UserLoginEnum result = userLoginService.toLogin(username,password);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);

    }
    //用户注册-手机号验证
    @PostMapping("/toRegiste")
    public ResultVo<Object> toRegiste(@RequestParam("telephone") String telephone){
            UserLoginEnum result = userRegisteService.toRegiste(telephone);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);

    }
    //用户注册-发送验证码
    @PostMapping("/getVerificationCode")
    public ResultVo<Object> getVerificationCode(@RequestParam("telephone") String telephone){
            UserLoginEnum result = userRegisteService.getVerificationCode(telephone);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);

    }
    //用户注册-验证码验证
    @PostMapping("/verifyCode")
    public ResultVo<Object> verifyCode(@RequestParam("telephone") String telephone,
                                       @RequestParam("code") String code){
            UserLoginEnum result = userRegisteService.verifyCode(telephone,code);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);

    }
    //用户注册-保存基本信息
    @PostMapping("/save")
    public ResultVo<Object> saveUserInfo(@Valid UserRegisteInfoForm userInfo,
                                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.GenerateErrorResult(ResultStatusCodeEnum.PARAM_ERROR.getCode(),
                    ResultStatusCodeEnum.PARAM_ERROR.getMessage());
        }
        Optional<UserBl> optionalUserBl = Optional.of(new UserBl());
        BeanUtils.copyProperties(userInfo,optionalUserBl.get());
        UserLoginEnum result = userRegisteService.saveInfo(optionalUserBl.get());
        return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
    }

}
