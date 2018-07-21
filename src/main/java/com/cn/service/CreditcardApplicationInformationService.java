package com.cn.service;

import com.cn.dataobject.CreditcardApplicationInformationTbl;
import com.cn.enums.CreditcardEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreditcardApplicationInformationService {

    CreditcardEnum save(CreditcardApplicationInformationTbl applicationInformationTbl);

    Page<CreditcardApplicationInformationTbl> findAll(Pageable pageable);

}
