package com.sw.goods.constent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/3
 */
public class Result {

    private static final Map<String, String> CODE_DESC = new HashMap<>();

    public static final String SUCCESS_CODE = "000";
    public static final String FAIL_CODE = "998";
    public static final String ERROR_CODE = "999";

    /**
     * 认证、注册
     */
    public static final String LOGIN_FAIL = "900";
    public static final String REGISTER_FAIL = "901";


    public static final String FEILD_ERROR = "800";


    public static final String ADD_FAIL = "700";
    public static final String UPDATE_FAIL = "701";
    public static final String DELETE_FAIL = "702";
    public static final String GET_FAIL = "703";

    static {
        CODE_DESC.put(SUCCESS_CODE, "请求成功");
        CODE_DESC.put(FAIL_CODE, "请求错误");
        CODE_DESC.put(ERROR_CODE, "系统发生错误");

        CODE_DESC.put(LOGIN_FAIL, "登入失败");
        CODE_DESC.put(REGISTER_FAIL, "注册失败");

        CODE_DESC.put(FEILD_ERROR, "字段错误");

        CODE_DESC.put(ADD_FAIL, "添加失败");
        CODE_DESC.put(UPDATE_FAIL, "更新失败");
        CODE_DESC.put(DELETE_FAIL, "删除失败");
        CODE_DESC.put(GET_FAIL, "获取失败");
    }

    public static String getDesc(String code) {
        return CODE_DESC.get(code);
    }



}
