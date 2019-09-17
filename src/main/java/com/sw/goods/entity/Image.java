package com.sw.goods.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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

    /**
     * 编号
     */
    @Column(name = "no", length = 30)
    @Length(max = 30, message = "编码长度最长为30")
    @JsonIgnore
    private String no;

    @Column(name = "IMAGE_URL", length = 512, nullable = false)
    @NotBlank(message = "图片地址不能为空")
    @Length(max = 512, message = "图片地址最大长度512")
    private String imageUrl;


    @Column(name = "NOTE", length = 70)
    @Length(max = 70, message = "说明的最大长度70")
    private String note;



}
