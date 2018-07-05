package com.cn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单表
 */
@Entity
@Data
public class OrderTbl {
    /**
     * 订单ID
     */
    @Id
    private Integer orderId;
    /**
     * 第三方支付订单编号
     */
    private String orderNo;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 创建订单时间
     */
    private Date createOrderDate;
    /**
     * 支付时间
     */
    private Date payDate;
    /**
     * 交易金额
     */
    private double fee;
    /**
     * 状态（1 成功 0 未支付 2 支付失败）
     */
    private Integer status;
    /**
     *备注
     */
    private String remark;

}
