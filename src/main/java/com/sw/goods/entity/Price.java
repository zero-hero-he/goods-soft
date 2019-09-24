package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 价格表
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Entity
@Table(name = "PRICE")
@Setter
@Getter
//动态赋值
@DynamicUpdate
//动态插入
@DynamicInsert
public class Price extends PriceInfo implements Serializable {
    private static final long serialVersionUID = -375615533446006655L;


}
