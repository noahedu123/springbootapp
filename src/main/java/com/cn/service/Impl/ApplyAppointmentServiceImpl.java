package com.cn.service.Impl;

import com.cn.Exception.appException;
import com.cn.dao.ApplyTblDao;
import com.cn.dataobject.ApplyTbl;
import com.cn.enums.ApplyAppointmentEnum;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.service.ApplyAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyAppointmentServiceImpl implements ApplyAppointmentService {

    @Autowired
    ApplyTblDao applyTblDao;

    /**
     * 需求预约保存
     * @param applyTbl
     * @return
     */
    @Override
    public ApplyAppointmentEnum save(ApplyTbl applyTbl) {
        ApplyTbl applyTbl1 = applyTblDao.save(applyTbl);
        if(applyTbl1 == null){
            throw new appException(ResultStatusCodeEnum.APPLY_SAVE_FAIL);
        }
        return ApplyAppointmentEnum.SUCCESS;
    }
}
