package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户登录online表
 */
@Entity
@Data
public class UserOnlineTbl {

    /**
     * 用户ID（user_login_tbl表主键）
     */
    @Id
    private Integer id;

    /**
     *  标识（登录后在线标识）
     */
    private String seySession;

    /**
     * 登录时间
     */
    private Date loginTime;
}
