package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.Util.DateUtil;
import com.cn.Util.HttpUtil;
import com.cn.Util.KeyUtil;
import com.cn.config.HttpServerConfig;
import com.cn.dao.UserBlDao;
import com.cn.dataobject.UserBl;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.PersonalSettingsService;
import com.cn.service.UserRegisteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Slf4j
public class PersonalSettingsServiceImpl implements PersonalSettingsService {
    @Autowired
    private UserBlDao userBlDao;
    @Autowired
    private UserRegisteService userRegisteService;
    @Autowired
    HttpServerConfig httpConfig;

    /**
     * 用户更换头像
     * @param telephone
     * @param file
     * @return 用户头像保存路径 118.89.248.60:11080/www/images/xxxxxxxx.jpg
     */
    @Override
    @Transactional
    public String changeAvatar(String telephone, MultipartFile file) {
        /**
         * 1.查询userbl用户相关信息 -->判断用户是否存在
         *      1.1 不存在 异常
         *      1.2  存在 -->判断用户是否上传头像
         *          1.2.1 是 --> 删除头像
         *      1.3 创建文件
         *      1.4 更新用户信息 -->判断是否成功
         *          1.4.1 失败  抛异常
         *          1.4.2 成功 返回用户头像路径
         */
        UserBl userBl = userBlDao.findUserBlByTelephone(telephone);
        if(userBl == null){
            throw new appException(ResultStatusCodeEnum.PARAM_ERROR);
        }
        try {
            if (!StringUtils.isEmpty(userBl.getAvatar())) {
                //获取用户原先 头像存放路径 /data/resource/www/image/83729343473.img 删除文件
                HttpUtil.sftpFileDelete(httpConfig.getIp(),httpConfig.getPort(),httpConfig.getUsername(),httpConfig.getPsw(),
                        httpConfig.getFilehome().concat(httpConfig.getAccesspath()),userBl.getAvatar());
            }
            String filename = file.getOriginalFilename();
            //生成用户新头像路径  83729343473.img
            String avatar = KeyUtil.getUniqueKey() +
                    filename.substring(filename.lastIndexOf("."));
            //保存用户头像文件至指定位置  /data/resource/www/image/83729343473.img
            HttpUtil.sftpFileUpload(httpConfig.getIp(),httpConfig.getPort(),httpConfig.getUsername(),httpConfig.getPsw(),
                                    httpConfig.getFilehome().concat(httpConfig.getAccesspath()),avatar,file.getInputStream());
//
            //更新用户信息
            UserBl newUserbl = new UserBl();
            BeanUtils.copyProperties(userBl, newUserbl);
            newUserbl.setAvatar(avatar);
            UserLoginEnum userLoginEnum = userRegisteService.saveInfo(newUserbl);
            if(userLoginEnum == null){
                throw new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
            }
            //118.89.248.60:11080/www/images/xxxxxxxx.jpg
            return ("http://").concat(httpConfig.getIp()).concat(":")+httpConfig.getHttpport()+httpConfig.getAccesspath().concat("/").concat(avatar);
        }catch (Exception e){
            log.error(e.getMessage());
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
            Date date = new Date();
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

    /**
     * 用户更新信息
     * @param telephone
     * @param nick
     * @param password
     * @return
     */
    @Override
    public UserLoginEnum updateSettings(String telephone, String nick, String password) {
        /**
         * 1.查询用户信息 -->判断是否为空
         *   1.1 空 -->返回用户未注册
         *   1.2 不空 --> 判断用户昵称是否为空
         *          1.2.1 不空 --> 更新用户昵称信息
         *          1.2.2 空 --> 判断用户密码是否为空
         *              1.2.2.1 不空--> 更新用户密码信息
         */
        UserBl userBl = userBlDao.findUserBlByTelephone(telephone);
        if(telephone == null ){//用户不存在 返回用户未注册
            return UserLoginEnum.REGISTERED;
        }else{
            if(nick != null){ //如果昵称不为空
                userBl.setNick(nick);
            }else if(password != null){ //如果密码不为空
                userBl.setPassword(password);
            }else{
                throw new appException(ResultStatusCodeEnum.PARAM_ERROR);
            }
        }
        UserBl result = userBlDao.save(userBl);
        if(result == null){
            throw new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
        }else{
            return UserLoginEnum.SUCCESS;
        }
    }
}
