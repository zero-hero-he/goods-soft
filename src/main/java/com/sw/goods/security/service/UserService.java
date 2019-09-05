package com.sw.goods.security.service;

import com.sw.goods.security.entity.User;

import java.sql.SQLException;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
public interface UserService {

    int addUser(User info) throws SQLException;

    User queryByUsername(String username);
}
