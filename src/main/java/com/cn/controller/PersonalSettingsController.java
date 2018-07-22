package com.cn.controller;

import com.cn.Exception.appException;
import com.cn.Util.FileUtil;
import com.cn.Util.KeyUtil;
import com.cn.Util.ResultUtil;
import com.cn.config.ImageLocationConfig;
import com.cn.dao.UserBlDao;
import com.cn.dataobject.UserBl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.PersonalSettingsService;
import com.cn.service.UserRegisteService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.jnlp.PersistenceService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.core.io.ResourceLoader;
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


}
