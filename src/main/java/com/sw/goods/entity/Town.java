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
@Table(name = "TOWN")
@Setter
@Getter
public class Town implements Serializable {

    private static final long serialVersionUID = 8389164068682098168L;
    @Id
    @Column(name = "_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "COUNTRY_ID", length = 12)
    private String countryId;

    @Column(name = "TOWN_ID", length = 12, unique = true)
    private String townId;
}
