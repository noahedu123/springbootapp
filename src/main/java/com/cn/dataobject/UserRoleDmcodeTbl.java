package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 系统管理员角色表
 */
@Entity
@Data
public class UserRoleDmcodeTbl {

    /**
     * 角色ID
     */
    @Id
    private Integer roleId;
    /**
     * 角色名称
     */
    private String name;
}
