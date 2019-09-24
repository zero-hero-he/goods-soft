package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/24
 */
@Getter
@Setter
//表明这是父类，可以将属性映射到子类中使用JPA生成表
@MappedSuperclass
//动态赋值
@DynamicUpdate
//动态插入
@DynamicInsert
public class PriceInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = -180879504215637338L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    @NotNull(message = "产品信息不能不空")
    protected Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID")
    @NotNull(message = "渠道信息不能不空")
    protected Channel channel;

    /**
     * 进货单价
     */
    @NotNull(message = "进货单价为空")
    @Column(name = "UNIT_PRICE", nullable = false, precision = 15, scale = 2)
    protected BigDecimal unitPrice;

    /**
     * 进货总价，金额
     */
    @NotNull(message = "进货总价为空")
    @Column(name = "SUM_PRICE", nullable = false, precision = 15, scale = 2)
    protected BigDecimal sumPrice;

    /**
     * 零售价
     */
    @NotNull(message = "零售价为空")
    @Column(name = "RETAIL_PRICE", nullable = false, precision = 15, scale = 2)
    protected BigDecimal retailPrice;

    @Column(name = "UNIT", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    protected UnitType unitType;

    /**
     * 数量
     */
    @Column(name = "COUNT")
    protected long count;

    @Column(name = "NOTE", length = 512)
    @Length(max = 512, message = "备注的最大长度为512")
    protected String note;

    @Column(name = "no", length = 30)
    @Length(max = 30, message = "编码长度最长为30")
    protected String no;
}
