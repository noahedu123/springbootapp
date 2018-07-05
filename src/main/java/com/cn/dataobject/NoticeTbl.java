package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公告表
 */
@Entity
@Data
public class NoticeTbl {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 发布时间
     */
    private Date publishTime;
}
