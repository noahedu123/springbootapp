package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 消息表
 */
@Entity
@Data
public class MessageTbl {

    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 推广类型
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date sendTime;

}
