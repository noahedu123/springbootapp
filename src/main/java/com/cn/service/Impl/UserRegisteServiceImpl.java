package com.cn.service.Impl;

import com.cn.Util.SmsUtil;
import com.cn.config.AccessKeyConfig;
import com.cn.config.TemplateConfig;
import com.cn.dao.UserLoginDao;
import com.cn.dataobject.UserLoginTbl;
import com.cn.dataobject.UserVerificationCode;
import com.cn.enums.UserLoginEnum;
import com.cn.service.UserRegisteService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

@Service
public class UserRegisteServiceImpl implements UserRegisteService {

    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    AccessKeyConfig accessKeyConfig;
    @Autowired
    TemplateConfig templateConfig;
    /**
     * 用户注册——手机号验证
     * @param telephone
     * @return UserLoginEnum
     */
    @Override
    public UserLoginEnum toRegiste(String telephone) {
       UserLoginTbl userLoginTbl= userLoginDao.findByAccName(telephone);
        /**
         * 不存在返回用户未注册，否则返回已注册
         */
       if(userLoginTbl==null){
           return UserLoginEnum.REGISTERED;
       }else{
           return UserLoginEnum.USER_HAS_REGISTER;
       }
    }

    @Override
    public UserLoginEnum getVerificationCode(String telephone) {
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

         String sendCode =(int)((Math.random()*9+1)*100000)+"";
        String templateParam="{\"code\":sendCode}";
        try{

            SendSmsResponse response =  SmsUtil.sendSms(accessKeyConfig.getAccessKeyId(),accessKeyConfig.getAccessKeySecret(), telephone,templateConfig.getSignName(),templateConfig.getTemplateCode(),templateParam);
            UserVerificationCode userVerificationCode=new UserVerificationCode();
            userVerificationCode.setBizid(response.getBizId());
            userVerificationCode.setTelephone(telephone);

        }catch (Exception e){
            return UserLoginEnum.USER_SENDMESSAGE_FAIL;
        }

        return UserLoginEnum.SUCCESS;
    }


}
