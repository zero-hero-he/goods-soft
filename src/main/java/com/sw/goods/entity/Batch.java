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
@Table(name = "BATCH")
@Setter
@Getter
public class Batch extends BaseInfo implements Serializable {


    private static final long serialVersionUID = 1479167772632156343L;
    @Column(name = "BATCH_ID", length = 30, unique = true)
    @NotNull
    @NotBlank(message = "批次号不能为空")
    @Length(min = 1, max = 30, message = "批次号长度为1~30")
    private String batchId;

    @Column(name = "name", length = 64)
    @Length(min = 1, max = 64, message = "批次名称长度为1~64")
    private String name;

}
