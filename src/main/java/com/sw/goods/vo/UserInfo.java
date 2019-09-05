package com.sw.goods.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/4
 */
@Getter
@Setter
public class UserInfo {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 16, message = "用户名长度为4~16")
    private String username;

    @NotBlank(message = "姓氏不可以为空")
    @Length(min = 1, max = 50, message = "姓氏的长度1~50")
    private String firstName;

    @NotBlank(message = "名字不可以为空")
    @Length(min = 1, max = 50, message = "名字的长度1~50")
    private String lastName;

    @NotBlank(message = "密码不可以为空")
    @Length(min = 6, max = 20, message = "密码的长度6~20")
    private String password;

    @Email(message = "邮箱的格式不正确")
    private String email;
}
