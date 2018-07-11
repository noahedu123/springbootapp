package com.cn.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户验证码表
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class UserVerificationCode {
    @Id
    private String telephone;
    private String code;
    private Date receiveDate;
    private String bizid;

}
