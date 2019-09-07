package com.sw.goods.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Entity
@Table(name = "IMAGE")
@Setter
@Getter
public class Image extends BaseInfo implements Serializable {
    private static final long serialVersionUID = -8200453490452804291L;

    @Column(name = "NAME", length = 512)
    @NotNull
    @NotBlank(message = "图片地址不能为空")
    @Length(max = 512, message = "图片地址最大长度512")
    private String imageUrl;


    @Column(name = "NOTE", length = 70)
    @Length(max = 70, message = "说明的最大长度70")
    private String note;



}
