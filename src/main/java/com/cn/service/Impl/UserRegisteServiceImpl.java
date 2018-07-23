package com.cn.service.Impl;

import com.cn.Util.DateUtil;
import com.cn.Util.SmsUtil;
import com.cn.config.AccessKeyConfig;
import com.cn.config.TemplateConfig;
import com.cn.constant.VerifyCodeConstant;
import com.cn.dao.UserBlDao;
import com.cn.dao.UserVerificationCodeDao;
import com.cn.dataobject.UserBl;
import com.cn.dataobject.UserVerificationCode;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserRegisteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cn.Exception.appException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
@Transactional
public class UserRegisteServiceImpl implements UserRegisteService {

    @Autowired
    private UserVerificationCodeDao userVerificationCodeDao;
    @Autowired
    AccessKeyConfig accessKeyConfig;
    @Autowired
    TemplateConfig templateConfig;
    @Autowired
    private UserBlDao userBlDao;

    Date date=new Date();
    /**;
     * 用户注册——手机号验证
     * @param telephone
     * @return UserLoginEnum
     */
    @Override
    public UserLoginEnum toRegiste(String telephone) {
       UserBl userBl = userBlDao.findUserBlByTelephone(telephone);
        /**
         * 不存在返回用户未注册，否则返回已注册
         */
       if(userBl==null){
           return UserLoginEnum.REGISTERED;
       }else{
           return UserLoginEnum.USER_HAS_REGISTER;
       }
    }

    /**
     * 用户注册——获取验证码
     * @param telephone
     * @return 返回获取验证码成功或失败
     */
    @Override
    public UserLoginEnum getVerificationCode(String telephone) {
        /**
         * 1.生成验证码
         * 2.调用接口发送验证码
         * 3.将验证码保存到数据库
         */
        String sendCode =(int)((Math.random()*9+1)*100000)+"";
        String templateParam="{\"code\":"+sendCode+"}";
        try{
            //调用接口发送验证码
            SendSmsResponse response = SmsUtil.sendSms(accessKeyConfig.getAccessKeyId(),accessKeyConfig.getAccessKeySecret(),
                    telephone,templateConfig.getSignName(),templateConfig.getTemplateCode(),templateParam);
            if(response.getCode().equals("OK")){//验证码发送返回结果OK
                UserVerificationCode userVerificationCode = new UserVerificationCode();
                userVerificationCode.setTelephone(telephone);
                userVerificationCode.setCode(sendCode);
                UserVerificationCode userVerificationCode1 = userVerificationCodeDao.save(userVerificationCode);
                if(userVerificationCode1 == null){//验证码保存失败
                    log.error(ResultStatusCodeEnum.USER_VERIFICATION_UPDATE_FAIL.getMessage());
                   throw new appException(ResultStatusCodeEnum.USER_VERIFICATION_UPDATE_FAIL);
                }
            }else{
                log.error(response.getCode());
                throw new appException(ResultStatusCodeEnum.USER_SENDMESSAGE_FAIL);
            }
        }catch (Exception e){//发送短信异常
            log.error(ResultStatusCodeEnum.USER_SENDMESSAGE_FAIL.getMessage());
            throw new appException(ResultStatusCodeEnum.USER_SENDMESSAGE_FAIL);
        }

        return UserLoginEnum.SUCCESS;
    }

    /**
     * 验证 手机验证码
     * @param telephone
     * @param code
     * @return UserLoginEnum.SUCCESS
     */
    @Override
    public UserLoginEnum verifyCode(String telephone, String code) {
        UserVerificationCode userVerificationCode = userVerificationCodeDao.findUserVerificationCodeByTelephone(telephone);
        /**
         * -->验证码是否存在
         *  1.1 验证码存在 -->是否超时
         *     1.1.1 不超时  --> 验证码是否正确
         *          1.1.1.1 正确 删除此条记录 返回成功
         *         1.1.1.2 错误 返回验证码错误信息
         *     1.1.2  超时  返回用户超时信息
         *  1.2 验证不存在（可能被删除掉） 返回用户超时信息
         */
        if(userVerificationCode!=null  ){//验证码存在
            if(DateUtil.hourMinutes(VerifyCodeConstant.EXPIRE).before(userVerificationCode.getReceiveDate())){//验证码不超时
                if(userVerificationCode.getCode().equals(code)){ //验证码正确
                    userVerificationCodeDao.deleteById(telephone);
                    return UserLoginEnum.SUCCESS;
                }else{ //验证码错误
                    return UserLoginEnum.VERIFY_CODE_ERROR;
                }
            }else{ //验证超时
                return  UserLoginEnum.VERIFY_CODE_TIMEOUT;
            }
        }else{
            return UserLoginEnum.VERIFY_CODE_TIMEOUT;
        }
    }

    /**
     * 保存用户信息
     * @param userBl
     * @return UserLoginEnum.SUCCESS
     */
    @Override
    public UserLoginEnum saveInfo(UserBl userBl) {
        userBl.setBindtime(new Date());
        UserBl userBl1 = userBlDao.save(userBl);
        if(userBl1 == null){
            log.error(ResultStatusCodeEnum.USERBL_SAVE_FAIL.getMessage());
            throw new appException(ResultStatusCodeEnum.USERBL_SAVE_FAIL);
        }
        return UserLoginEnum.SUCCESS;
    }
}
