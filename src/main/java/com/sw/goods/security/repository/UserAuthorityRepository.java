package com.sw.goods.security.repository;

import com.sw.goods.security.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
