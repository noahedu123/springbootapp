package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.Util.DateUtil;
import com.cn.Util.FileUtil;
import com.cn.Util.KeyUtil;
import com.cn.config.ImageLocationConfig;
import com.cn.dao.UserBlDao;
import com.cn.dataobject.UserBl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.PersonalSettingsService;
import com.cn.service.UserRegisteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.jnlp.PersistenceService;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Service
@Slf4j
public class PersonalSettingsServiceImpl implements PersonalSettingsService {
    @Autowired
    private UserBlDao userBlDao;
    @Autowired
    private UserRegisteService userRegisteService;
    @Autowired
    ImageLocationConfig imageLocationConfig;

    /**
     * 用户更换头像
     * @param telephone
     * @param file
     * @return UserLoginEnum
     */
    @Override
    @Transactional
    public UserLoginEnum changeAvatar(String telephone, MultipartFile file) {
        /**
         * 1.查询userbl用户相关信息 -->判断用户是否存在
         *      1.1 不存在 异常
         *      1.2  存在 -->判断用户是否上传头像
         *          1.2.1 是 --> 删除头像
         *      1.3 创建文件
         *      1.4 更新用户信息 -->判断是否成功
         *          1.4.1 失败  抛异常
         *          1.4.2 成功 返回成功信息
         */
        UserBl userBl = userBlDao.findUserBlByTelephone(telephone);
        if(userBl == null){
            throw new appException(ResultStatusCodeEnum.PARAM_ERROR);
        }
        try {
            if (!StringUtils.isEmpty(userBl.getAvatar())) {
                //获取用户原先头像存放路径 xxx/image/13729342273.img 删除文件
                File target = new File(FileUtil.createImageUrl("") + "/" + userBl.getAvatar());
                target.delete();
            }
            String filename = file.getOriginalFilename();
            //生成用户新头像路径  83729343473.img
            String avatar = KeyUtil.getUniqueKey() +
                    filename.substring(filename.lastIndexOf("."));
            //保存用户头像文件至指定位置  xxxx/image/83729343473.img
            File target = new File(FileUtil.createImageUrl(imageLocationConfig.getLocation()) + "/" + avatar);
            FileOutputStream out = new FileOutputStream(target);
            IOUtils.copy(file.getInputStream(), out);
            //更新用户信息
            UserBl newUserbl = new UserBl();
            BeanUtils.copyProperties(userBl, newUserbl);
            newUserbl.setAvatar(imageLocationConfig.getLocation() + avatar);
            UserLoginEnum userLoginEnum = userRegisteService.saveInfo(newUserbl);
            if(userLoginEnum == null){
                throw new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
            }
            return UserLoginEnum.SUCCESS;
        }catch (Exception e){
            throw new appException(ResultStatusCodeEnum.UPLOAD_IMAGES_FAIL);
        }
    }

    /**
     * 更换手机号
     * @param oldtelephone
     * @param newtelephone
     * @return
     */
    @Override
    @Transactional
    public UserLoginEnum changeTelephone(String oldtelephone, String newtelephone) {
        /**
         * 1.查询用户信息 -->判断是否为空
         *   1.1 空 -->抛出异常
         *   1.2 不空 --> 判断用户是否可以更改手机号（30内不许更换）
         *          1.2.1 可以 --> 更新用户信息
         *          1.2.2 不可以 返回用户手机号30天内不能改
         */
        UserBl userbl = userBlDao.findUserBlByTelephone(oldtelephone);
        if(userbl == null){
            throw  new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
        }else{
            Date date =new Date();
            //判断用户bindtime绑定手机号时间是否超过30天
            if(userbl.getBindtime()!=null && userbl.getBindtime().before(DateUtil.getDateBefore(date,30))){
                UserBl newUserbl = new UserBl();
                BeanUtils.copyProperties(userbl,newUserbl);
                newUserbl.setTelephone(newtelephone);
                newUserbl.setBindtime(date);
                UserBl result = userBlDao.save(newUserbl);
                if(result == null ){ //更新用户信息失败
                    throw  new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
                }else{ //更新用户信息成功
                    return UserLoginEnum.SUCCESS;
                }
            }else{ //用户手机号绑定少于30天
                return UserLoginEnum.TELEPHONE_CHANGE_NOT_ALLOW;
            }
        }
    }
}
