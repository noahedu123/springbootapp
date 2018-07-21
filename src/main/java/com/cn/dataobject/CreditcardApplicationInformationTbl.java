package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 信用卡申请信息表
 */
@Entity
@Data
public class CreditcardApplicationInformationTbl {

    /**
     *信用卡申请信息ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *申请人姓名
     */
    private String applyname;
    /**
     *身份证号码
     */
    private String idcardnumber;
    /**
     *电话号码
     */
    private String phonenumber;
    /**
     *登录用户申请卡
     */
    private String phonenumbermaster;

}
