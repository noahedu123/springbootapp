package com.cn.dao;

import com.cn.dataobject.UserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationCodeDao extends JpaRepository<UserVerificationCode,String> {
    UserVerificationCode findUserVerificationCodeByTelephone(String telephone);

}
