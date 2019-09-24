package com.sw.goods.service;

import com.sw.goods.entity.HistoryPrice;
import com.sw.goods.exception.SoftException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/17
 */
public interface HistoryPriceService {

    Page<HistoryPrice> queryHistoryPrice(List<Long> productIds, List<Long> channelIds, String no, int pageNumber, int pageSize);

    HistoryPrice getById(long id);

    HistoryPrice add(HistoryPrice price) throws SoftException;
}
