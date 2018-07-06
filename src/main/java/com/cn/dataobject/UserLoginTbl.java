package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户登录信息表
 */
@Entity
@Data
public class UserLoginTbl {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *用户名（手机号作为登录唯一标识）
     */
    private String accName;

    /**
     *密码（加密后的字符串）
     */
    private String password;
    /**
     * 登录失败次数
     */
    private Integer failureTimes;
    /**
     * 锁定时间
     */
    private Date lockTime;
}
