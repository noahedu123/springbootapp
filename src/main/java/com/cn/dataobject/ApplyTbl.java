package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 需求申请表 hyf
 */
@Entity
@Data
public class ApplyTbl {
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *姓名
     */
    private String name;
    /**
     *身份证号码
     */
    private String idCard;
    /**
     *手机号
     */
    private String phone;
    /**
     *微信号
     */
    private String wechat;
    /**
     *公司名称
     */
    private String companyName;
    /**
     *申请金额
     */
    private double applyMoney;
    /**
     *提交类型
     */
    private Integer type;
    /**
     *申请时间
     */
    private Date applyTime;
    /**
     *审核状态
     */
    private Integer status;
    /**
     *需求详情
     */
    private String detail;
    /**
     *取消理由
     */
    private String reason;
    /**
     *操作时间
     */
    private Date update;


}
