package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 省份
 *
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/6
 */
@Entity
@Table(name = "PROVINCE")
@Setter
@Getter
public class Province implements Serializable {


    private static final long serialVersionUID = -7638395985023768851L;
    @Id
    @Column(name = "_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 64)
    @NotNull
    private String name;

    @Column(name = "PROVINCE_ID", length = 12, unique = true)
    @NotNull
    private String provinceId;
}
