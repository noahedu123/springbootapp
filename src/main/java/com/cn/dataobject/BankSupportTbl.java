package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 支持银行表
 */
@Entity
@Data
public class BankSupportTbl {
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 银行名称
     */
    private String name;
    /**
     * LOGO
     */
    private String logo;
    /**
     * 网页地址
     */
    private String netUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 奖励系统
     */
    private String rewardCoef;
}
