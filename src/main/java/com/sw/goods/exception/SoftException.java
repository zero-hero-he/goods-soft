package com.sw.goods.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/5
 */
@Setter
@Getter
public class SoftException extends Exception {

    private String errorCode;

    public SoftException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
