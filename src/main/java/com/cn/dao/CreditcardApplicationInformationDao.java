package com.cn.dao;

import com.cn.dataobject.CreditcardApplicationInformationTbl;
import com.cn.dataobject.UserVerificationCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditcardApplicationInformationDao extends JpaRepository<CreditcardApplicationInformationTbl,String> {


    Page<CreditcardApplicationInformationTbl> findAll(Pageable pageable);

}
