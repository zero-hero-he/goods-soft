package com.sw.goods.security.repository;

import com.sw.goods.security.AuthorityName;
import com.sw.goods.security.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(AuthorityName name);

}
