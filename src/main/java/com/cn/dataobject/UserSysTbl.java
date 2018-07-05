package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 系统管理员表
 */
@Entity
@Data
public class UserSysTbl {
    /**
     * 用户Id
     */
    @Id
    private  Integer userId;
    /**
     * 角色Id
     */
    private Integer roleId;
}
