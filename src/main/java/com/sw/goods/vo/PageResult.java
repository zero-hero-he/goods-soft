package com.sw.goods.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Setter
@Getter
public class PageResult<T> {

    private List<T> data;

    private Integer totalPages;

    private Long totalElements;

    public PageResult() {
    }

    public PageResult(Page<T> page) {
        this.data = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

}
