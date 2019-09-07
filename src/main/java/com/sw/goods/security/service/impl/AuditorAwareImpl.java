package com.sw.goods.security.service.impl;

import com.sw.goods.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Slf4j
@Component
public class AuditorAwareImpl implements AuditorAware<User> {

    private Optional<User> auditor = Optional.empty();

    /**
     * @param auditor set操作人
     */
    public void setAuditor(User auditor) {
        this.auditor = Optional.of(auditor);
    }


    @Override
    public Optional<User> getCurrentAuditor() {
//        User user = null;
//        boolean present = auditor.isPresent();
//        if (!present) {
//            try {
//                //获取当前用户
////                user = JwtUtil.getUser();
//            } catch (Exception e) {
//                log.error("更新&添加--->用户获取失败", e);
//            }
//            if (user != null) {
//                auditor = auditor.of(user);
//            }
//        }
//        return auditor;

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    }
}
