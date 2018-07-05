package com.cn.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 资讯表
 */
@Entity
@Data
public class InformationTbl {

    /**
     * id
     */
    @Id
    private Integer id;
    /**
     *标题
     */
    private String title;
    /**
     *作者
     */
    private String author;
    /**
     *标题图片
     */
    private String titleImgUrl;
    /**
     *正文图片
     */
    private String contImgUrl;
    /**
     *展示
     */
    private Integer showFlag;
    /**
     *正文
     */
    private String content;
    /**
     *发布时间
     */
    private Date publishTime;

}
