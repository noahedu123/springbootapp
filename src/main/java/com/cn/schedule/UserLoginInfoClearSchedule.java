package com.cn.schedule;

import com.cn.dao.UserLoginDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cn.enums.ResultStatusCodeEnum;
import java.util.List;
@Slf4j
@Component
public class UserLoginInfoClearSchedule {
    @Autowired
    private UserLoginDao userLoginDao;

    /**
     * 定时清空用户登录错误信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public  void ClearUserLoginInfo(){
                try {
                    userLoginDao.deleteAll();
                }catch (Exception e){
                    log.error(ResultStatusCodeEnum.SCHEDULE_DELETE_USER_LOGIN_FAIL.getMessage());
                }
    }
}
