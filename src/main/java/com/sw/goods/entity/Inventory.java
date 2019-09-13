package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 库存表
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Entity
@Table(name = "INVENTORY")
@Setter
@Getter
public class Inventory extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 3516369014156136061L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HISTORY_PRICE_ID")
    private HistoryPrice historyPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    /**
     * 库存数量
     */
    @Column(name = "INVENTORY_COUNT", nullable = false)
    private long inventoryCount;

    /**
     * 总量
     */
    @Column(name = "ALL_COUNT", nullable = false)
    private long allCount;
}
