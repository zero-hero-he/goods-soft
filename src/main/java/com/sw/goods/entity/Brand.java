package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 厂商信息表
 *
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/6
 */
@Entity
@Table(name = "BRAND")
@Setter
@Getter
public class Brand {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "brand_seq")
    private Long id;

    @Column(name = "NAME", length = 64)
    @NotNull
    private String name;

    @Column(name = "FULL_NAME", length = 254)
    @NotNull
    private String fullName;

    @Column(name = "CONTACT", length = 20)
    private String contact;

    @Column(name = "PROVINCE_ID", length = 12)
    private Integer provinceId;

    @Column(name = "CITY_ID", length = 12)
    private Integer cityId;

    @Column(name = "COUNTRY_ID", length = 12)
    private String countryId;

    @Column(name = "TOWN_ID", length = 12)
    private String townId;

    @Column(name = "VILLAGE_ID", length = 12)
    private String villageId;

    @Column(name = "ADDRESS", length = 512)
    private String address;

    @Transient
    private String provinceName;

    @Transient
    private String cityName;

    @Transient
    private String countryName;

    @Transient
    private String townName;

    @Transient
    private String villageName;


}
