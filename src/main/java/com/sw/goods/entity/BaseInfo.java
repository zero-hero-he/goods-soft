package com.sw.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sw.goods.security.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.ForeignKey;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Getter
@Setter
//表明这是父类，可以将属性映射到子类中使用JPA生成表
@MappedSuperclass
//动态赋值
@DynamicUpdate
//动态插入
@DynamicInsert
public class BaseInfo implements Serializable {

    private static final long serialVersionUID = -7225292834477621970L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "brand_seq")
    protected Long id;

    @Column(name = "CREATE_TIME")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date createTime;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @UpdateTimestamp
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date updateTime = new Date();

    /**
     * 创建人
     */
    @CreatedBy
    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "CREATE_USER_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private User createUser;
    /**
     * 修改人
     */
    @LastModifiedBy
    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "UPDATE_USER_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private User updateUser;

}
