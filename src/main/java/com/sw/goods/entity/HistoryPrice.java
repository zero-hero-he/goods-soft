package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
public class HistoryPrice extends PriceInfo implements Serializable {
    private static final long serialVersionUID = -375615533446006655L;

}
