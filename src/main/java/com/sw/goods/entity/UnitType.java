package com.sw.goods.entity;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public enum UnitType {

    /**
     * 个
     */
    A("个"),
    /**
     * 袋
     */
    BAG("袋"),
    /**
     * 包
     */
    BAL("包"),
    /**
     * 桶
     */
    BAR("桶"),
    /**
     * 捆
     */
    BDL("捆"),
    /**
     * 箱
     */
    BOX("箱"),
    /**
     * 盒
     */
    BOX2("盒"),
    /**
     * 卷
     */
    ROL("卷"),
    /**
     * 件
     */
    PIE("件"),
    /**
     * 只
     */
    PIECE("只"),
    /**
     * 套
     */
    SET("套"),
    /**
     * 打
     */
    DOZ("打"),
    /**
     * 双
     */
    PAIR("双"),
    /**
     * 毫米
     */
    MM("毫米"),
    /**
     * 厘米
     */
    CM("厘米"),
    /**
     * 米
     */
    M("米"),
    /**
     * 千米
     */
    KM("千米"),
    /**
     * 克
     */
    G("克"),
    /**
     * 千克
     */
    KG("千克"),
    /**
     * 吨
     */
    T("吨"),
    /**
     * 两
     */
    LIANG("两"),
    /**
     * 斤
     */
    JIN("斤");

    private String desc;

    UnitType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}


