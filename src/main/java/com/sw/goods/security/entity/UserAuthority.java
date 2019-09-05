package com.sw.goods.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "USER_AUTHORITY", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "AUTHORITY_ID"}))
public class UserAuthority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USER_ID")
    @NotNull
    private long userId;

    @Column(name = "AUTHORITY_ID")
    @NotNull
    private long authorityId;

    public UserAuthority(@NotNull long userId, @NotNull long authorityId) {
        this.userId = userId;
        this.authorityId = authorityId;
    }
}