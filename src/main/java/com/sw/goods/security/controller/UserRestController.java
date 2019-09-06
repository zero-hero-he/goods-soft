package com.sw.goods.security.controller;

import com.sw.goods.constent.Result;
import com.sw.goods.exception.SoftException;
import com.sw.goods.security.entity.User;
import com.sw.goods.security.jwt.JwtTokenUtil;
import com.sw.goods.security.jwt.JwtUser;
import com.sw.goods.security.service.UserService;
import com.sw.goods.vo.HttpResult;
import com.sw.goods.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Slf4j
@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public HttpResult<JwtUser> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return new HttpResult<>(user);
    }

    @PostMapping("/auth/register")
    public HttpResult<String> register(@RequestBody UserInfo userInfo) throws SoftException, SQLException {
        if (userService.queryByUsername(userInfo.getUsername()) != null) {
            throw new SoftException(Result.REGISTER_FAIL, "该用户名已存在!");
        }
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setEmail(userInfo.getEmail());
        user.setPassword(userInfo.getPassword());
        if (userService.addUser(user) == 1) {
            return new HttpResult<>(Result.SUCCESS_CODE, "注册成功!");
        } else {
            throw new SoftException(Result.REGISTER_FAIL, "注册失败!");
        }
    }

}
