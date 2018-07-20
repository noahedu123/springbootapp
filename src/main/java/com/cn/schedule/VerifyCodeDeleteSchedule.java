package com.cn.schedule;

import com.cn.Util.DateUtil;
import com.cn.constant.VerifyCodeConstant;
import com.cn.dao.UserVerificationCodeDao;
import com.cn.dataobject.UserVerificationCode;
import com.cn.enums.ResultStatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cn.Exception.appException;
import java.util.List;

@Component
public class VerifyCodeDeleteSchedule {
    @Autowired
    private UserVerificationCodeDao userVerificationCodeDao;

    /**
     * 定时删除无效验证码
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public  void DeleteVerifyCode(){
        /**
         * 查询验证码表，如果验证码超时(大于VerifyCodeConstant.EXPIRE)，则删除
         */
        List<UserVerificationCode> codeList = userVerificationCodeDao.findAll();
        for(UserVerificationCode code :codeList){
            if(DateUtil.hourMinutes(VerifyCodeConstant.EXPIRE).after(code.getReceiveDate())){
                try {
                    userVerificationCodeDao.delete(code);
                }catch (Exception e){
                    throw new appException(ResultStatusCodeEnum.SCHEDULE_DELETE_VERIFYCODE_FAIL);
                }
            }
        }
    }

}
