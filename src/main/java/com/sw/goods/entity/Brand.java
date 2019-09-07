package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
public class Brand extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 7690124185128387480L;
    @Column(name = "NAME", length = 64)
    @NotNull
    @NotBlank(message = "品牌简称不能为空")
    @Length(min = 1, max = 64, message = "品牌简称为1~64")
    private String name;

    @Column(name = "FULL_NAME", length = 254)
    @NotNull
    @NotBlank(message = "品牌全称不能为空")
    @Length(min = 1, max = 254, message = "品牌全称为1~254")
    private String fullName;

    @Column(name = "CONTACT", length = 20)
    @Length(max = 20, message = "联系电话的最大长度为20")
    private String contact;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "PROVINCE_ID", referencedColumnName = "PROVINCE_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Province province;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private City city;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Country country;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Town town;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "VILLAGE_ID", referencedColumnName = "VILLAGE_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Village village;

    @Column(name = "ADDRESS", length = 512)
    @Length(max = 512, message = "详细地址超长")
    private String address;

    @Column(name = "NOTE", length = 512)
    @Length(max = 512, message = "备注的最大长度为512")
    private String note;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private List<Image> images;


}
