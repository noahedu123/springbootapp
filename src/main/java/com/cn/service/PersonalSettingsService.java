package com.cn.service;
import com.cn.enums.UserLoginEnum;
import org.springframework.web.multipart.MultipartFile;

public interface PersonalSettingsService {
    UserLoginEnum changeAvatar(String telephone , MultipartFile file);
    UserLoginEnum changeTelephone(String oldtelephone , String newtelephone);
}
