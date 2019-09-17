package com.sw.goods.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/17
 */
public class ToolUtil {

    public static String timeNo(){
        return DateFormatUtils.format(new Date(), "yyMMddHHmmss");
    }

}
