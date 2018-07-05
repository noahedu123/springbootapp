package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 操作日志表
 */
@Entity
@Data
public class OptionLogTbl {
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 操作人ID
     */
    private Integer opMan;
    /**
     * 操作时间
     */
    private Integer opTime;
    /**
     * 备注
     */
    private String remark;
}
