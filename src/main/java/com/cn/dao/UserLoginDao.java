package com.cn.dao;

import com.cn.dataobject.UserLoginTbl;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserLoginDao extends JpaRepository<UserLoginTbl,String> {
    UserLoginTbl findByAccName(String accname);

}
