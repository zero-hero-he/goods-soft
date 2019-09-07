package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 历史价格表
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Entity
@Table(name = "HISTORY_PRICE")
@Setter
@Getter
public class HistoryPrice extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -375615533446006655L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channel;

    /**
     * 进货单价
     */
    @NotNull(message = "进货单价为空")
    @Column(name = "UNIT_PRICE", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    /**
     * 进货总价，金额
     */
    @NotNull(message = "进货总价为空")
    @Column(name = "SUM_PRICE", nullable = false, precision = 15, scale = 2)
    private BigDecimal sumPrice;

    /**
     * 零售价
     */
    @NotNull(message = "零售价为空")
    @Column(name = "RETAIL_PRICE", nullable = false, precision = 15, scale = 2)
    private BigDecimal retailPrice;

    @Column(name = "UNIT", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    /**
     * 数量
     */
    @Column(name = "COUNT")
    private long count;

    @Column(name = "NOTE", length = 512)
    @Length(max = 512, message = "备注的最大长度为512")
    private String note;


}
