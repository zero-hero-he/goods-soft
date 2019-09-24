package com.sw.goods.service.impl;

import com.sw.goods.entity.HistoryPrice;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.HistoryPriceRepository;
import com.sw.goods.service.HistoryPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/24
 */
@Service
public class HistoryPriceServiceImpl implements HistoryPriceService {

    @Autowired
    private HistoryPriceRepository historyPriceRepository;

    @Override
    public Page<HistoryPrice> queryHistoryPrice(List<Long> productIds, List<Long> channelIds, String no, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return historyPriceRepository.queryPrice(productIds, channelIds, no, pageable);
    }

    @Override
    public HistoryPrice getById(long id) {
        return historyPriceRepository.getOne(id);
    }

    @Override
    public HistoryPrice add(HistoryPrice price) throws SoftException {
        return historyPriceRepository.save(price);
    }
}
