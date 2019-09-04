package com.sw.goods.vo;

import com.sw.goods.constent.Result;
import lombok.Data;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/3
 */
@Data
public class HttpResult<T> {

    private String resultCode;
    private String resultMsg;
    private T data;


    public HttpResult(String resultCode, String resultMsg, T data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public HttpResult(T data) {
        this(Result.SUCCESS_CODE, data);
    }

    public HttpResult(String resultCode) {
        this.resultCode = resultCode;
    }

    public HttpResult(String resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
        this.resultMsg = Result.getDesc(resultCode);
    }

    public HttpResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
