package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 产品表
 */
@Entity
@Data
public class ProductTbl {
    /**
     *id
     */
    @Id
    private Integer id;
    /**
     *标题
     */
    private String title;
    /**
     *卡 LOGO
     */
    private Integer cardLogo;
    /**
     *简介
     */
    private String summary;
    /**
     *缩略图
     */
    private String smallImgUrl;
    /**
     *正文图片
     */
    private String contImgUrl;
    /**
     *轮播图
     */
    private String bannerImgUrl;
    /**
     *产品类型（1 办卡产品 2 贷款产品）
     */
    private Integer type;
    /**
     *正文
     */
    private String content;
    /**
     *奖励系数
     */
    private String rewardCoef;

}
