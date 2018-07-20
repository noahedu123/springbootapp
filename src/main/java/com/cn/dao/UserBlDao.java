package com.cn.dao;

import com.cn.dataobject.UserBl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlDao extends JpaRepository<UserBl,String> {
    UserBl findUserBlByTelephone(String telephone);
}
