package com.cn.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户绑定第三方平台表
 */
@Entity
@Data
public class UserBindThirdTbl {
    /**
     * 用户ID（user_login_tbl表主键）
     */
    @Id
    private Integer id;

    /**
     * 标识（第三方提供应用的唯一标示）
     */
    private String thirdToken;

    /**
     *渠道id（1 微信 2 QQ 3 新浪）
     */
    private Integer site;
}
