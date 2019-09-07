package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/6
 */
@Entity
@Table(name = "COUNTRY")
@Setter
@Getter
public class Country implements Serializable {

    private static final long serialVersionUID = 4340224893321190494L;
    @Id
    @Column(name = "_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 64)
    @NotNull
    private String name;

    @Column(name = "COUNTRY_ID", length = 12, unique = true)
    @NotNull
    private String countryId;

    @Column(name = "CITY_ID", length = 12)
    @NotNull
    private String cityId;
}
