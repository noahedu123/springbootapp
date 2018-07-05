package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *  系统管理权限角色关系表
 */
@Entity
@Data
public class SysAuthDcodeTbl {
    /**
     * 角色ID
     */
    @Id
    private Integer roleId;
    /**
     * 权限ID
     */
    private Integer authId;
}
