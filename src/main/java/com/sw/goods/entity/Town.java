package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/6
 */
@Entity
@Table(name = "TOWN")
@Setter
@Getter
public class Town {

    @Id
    @Column(name = "_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 64)
    @NotNull
    private String name;

    @Column(name = "COUNTRY_ID", length = 12)
    @NotNull
    private String countryId;

    @Column(name = "TOWN_ID", length = 12, unique = true)
    @NotNull
    private String townId;
}
