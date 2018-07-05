package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 系统管理权限表
 */
@Entity
@Data
public class SysAuthDmcodeTbl {
    /**
     * 权限Id
     */
    @Id
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     *  父权限
     */
    private Integer parantId;
}
