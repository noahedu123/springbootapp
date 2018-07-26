package com.cn.service;

import com.cn.dataobject.ApplyTbl;
import com.cn.enums.ApplyAppointmentEnum;

public interface ApplyAppointmentService {
    ApplyAppointmentEnum save(ApplyTbl applyTbl);
}
