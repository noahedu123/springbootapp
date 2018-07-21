package com.cn.service.Impl;

import com.cn.dao.CreditcardApplicationInformationDao;
import com.cn.dataobject.CreditcardApplicationInformationTbl;
import com.cn.enums.CreditcardEnum;
import com.cn.service.CreditcardApplicationInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 信用卡申请
 */
@Service
@Slf4j
public class CreditcardApplicationInformationServiceImpl implements CreditcardApplicationInformationService {
    @Autowired
    private CreditcardApplicationInformationDao applicationInformationDao;

    /**
     * 保存申请信用卡信息
     * @param applicationInformationTbl
     * @return
     */
    @Override
    public CreditcardEnum save(CreditcardApplicationInformationTbl applicationInformationTbl) {
        CreditcardApplicationInformationTbl informationTbl = applicationInformationDao.save(applicationInformationTbl);
        if (informationTbl !=null){
            return CreditcardEnum.SUCCESS;
        }else {
            return  CreditcardEnum.FAIL;
        }
    }

    @Override
    public Page<CreditcardApplicationInformationTbl> findAll(Pageable pageable) {


        Page<CreditcardApplicationInformationTbl> applicationInformationTblPage = applicationInformationDao.findAll(pageable);

       // return new PageImpl<CreditcardApplicationInformationTbl>();
        return  null;
    }
}
