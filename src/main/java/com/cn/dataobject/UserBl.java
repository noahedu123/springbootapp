package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户信息详细表
 */
@Entity
@Data
public class UserBl {
    /**
     *用户ID（user_login_tbl表主键）
     */
    @Id
    private Integer id;
    /**
     *用户昵称
     */
    private String nick;
    /**
     *头像
     */
    private String avatar;
    /**
     *性别（1 男 2 女）
     */
    private Integer sex;
    /**
     *电子邮箱
     */
    private String email;
    /**
     *级别
     */
    private Integer level;
    /**
     *佣金
     */
    private double money;
    /**
     *密码（加密后的字符串）
     */
    private String passWord;
    /**
     *注册时间
     */
    private Date time;
    /**
     *注册时所在城市
     */
    private String registerCity;
    /**
     *是不是会员（1 是  2 否）
     */
    private Integer membership;
    /**
     *有效期开始时间
     */
    private Date membershipStart;
    /**
     *有效期结束时间
     */
    private Date membershipEnd;

}
