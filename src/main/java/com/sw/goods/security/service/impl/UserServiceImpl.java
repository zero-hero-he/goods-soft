package com.sw.goods.security.service.impl;

import com.sw.goods.security.AuthorityName;
import com.sw.goods.security.entity.Authority;
import com.sw.goods.security.entity.User;
import com.sw.goods.security.entity.UserAuthority;
import com.sw.goods.security.repository.AuthorityRepository;
import com.sw.goods.security.repository.UserAuthorityRepository;
import com.sw.goods.security.repository.UserRepository;
import com.sw.goods.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(rollbackFor = SQLException.class)
    public int addUser(User info) throws SQLException {
        info.setPassword(passwordEncoder.encode(info.getPassword()));
        info.setEnabled(true);
        info.setLastPasswordResetDate(new Date());
        User dbUser = userRepository.save(info);
        long userId = dbUser.getId();
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_USER);
        long authId = authority.getId();
        UserAuthority userAuthority = new UserAuthority(userId, authId);
        userAuthorityRepository.save(userAuthority);
        return 1;
    }

    @Override
    public User queryByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
