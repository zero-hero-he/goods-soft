package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */

@Entity
@Table(name = "PRODUCT")
@Setter
@Getter
public class Product extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 3382626126786937311L;

    @Column(name = "NAME", length = 30)
    @NotNull
    @NotBlank(message = "产品名称不能为空")
    @Length(min = 1, max = 30, message = "产品名称长度为1~30")
    private String name;

    /**
     * 型号
     */
    @Column(name = "MODEL", length = 40)
    @Length(max = 40, message = "型号长度最长为40")
    private String model;

    /**
     * 规格
     */
    @Column(name = "SPECIFICATION", length = 30)
    @Length(max = 30, message = "规格长度最长为30")
    private String specification;

    /**
     * 编号
     */
    @Column(name = "no", length = 30)
    @Length(max = 30, message = "编码长度最长为30")
    private String no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    @NotNull(message = "品牌不能为空")
    private Brand brand;

    @OneToMany(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(
            name = "IMAGE_LIST",
            joinColumns = {@JoinColumn(name = "NO", referencedColumnName = "NO")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")})
    private List<Image> images;



}
