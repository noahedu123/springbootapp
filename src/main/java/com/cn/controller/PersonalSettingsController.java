package com.cn.controller;

import com.cn.Util.ResultUtil;
import com.cn.enums.UserLoginEnum;
import com.cn.service.PersonalSettingsService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/PersonalSetting")
@Slf4j
public class PersonalSettingsController {
    @Autowired
    private PersonalSettingsService personalSettingsService;

    /**
     * 保存用户头像
     * @param file
     * @param telephone
     * @return
     */
    @PostMapping("/saveAvatar")
    public ResultVo<Object> saveAvatar(@RequestParam("file")MultipartFile file,
                                 @RequestParam("telephone") String telephone){
        String result = personalSettingsService.changeAvatar(telephone,file);
        return ResultUtil.GenerateSuccessResult(UserLoginEnum.SUCCESS.getCode(),
                UserLoginEnum.SUCCESS.getMessage(),result);
    }

    /**
     * 用户更换手机号
     * @param oldTelephone
     * @param newTelephone
     * @return
     */
    @PostMapping("/changeTelephone")
    public ResultVo<Object> changeTelephone(@RequestParam("oldTelephone")String oldTelephone,
                                            @RequestParam("newTelephone") String newTelephone){
        UserLoginEnum result = personalSettingsService.changeTelephone(oldTelephone,newTelephone);
        return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);
    }
    @PostMapping("/updateSettings")
    public ResultVo<Object> updateSettings(@RequestParam("telephone")String telephone,
                                           @RequestParam(value = "password",required = false)String password,
                                           @RequestParam(value = "nick",required = false)String nick){
        return null;
    }


}
