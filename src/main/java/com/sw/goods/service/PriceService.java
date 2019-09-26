package com.sw.goods.service;

import com.sw.goods.entity.Price;
import com.sw.goods.exception.SoftException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/17
 */
public interface PriceService {

    Page<Price> queryPrice(Long[] productIds, Long[] channelIds, String no, int pageNumber, int pageSize);

    Price getById(long id);

    Price add(Price price) throws SoftException;

    Price update(Price price) throws SoftException;

    void delete(Long id) throws SoftException;

    int delete(List<Long> ids) throws SoftException;
}
