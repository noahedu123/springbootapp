package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 轮播广告表
 */
@Entity
@Data
public class BannerTbl {
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     *名称
     */
    private String name;
    /**
     *图片地址
     */
    private String imgUrl;
    /**
     *网页地址
     */
    private String netUrl;
    /**
     *排序
     */
    private Integer sort;
    /**
     *轮播图类型（1 首页 2 信用卡 3 贷款 4 发现）
     */
    private Integer type;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *显示开始时间（在设置时间内才显示，可不填）
     */
    private Date startTime;
    /**
     *显示结束时间（在设置时间内才显示，可不填）
     */
    private Date endTime;


}
