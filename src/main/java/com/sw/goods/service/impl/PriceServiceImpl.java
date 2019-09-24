package com.sw.goods.service.impl;

import com.sw.goods.constent.Result;
import com.sw.goods.entity.HistoryPrice;
import com.sw.goods.entity.Price;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.ChannelRepository;
import com.sw.goods.repository.PriceRepository;
import com.sw.goods.repository.ProductRepository;
import com.sw.goods.service.HistoryPriceService;
import com.sw.goods.service.PriceService;
import com.sw.goods.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ChannelRepository channelRepository;

    private HistoryPriceService historyPriceService;

    @Override
    public Page<Price> queryPrice(List<Long> productIds, List<Long> channelIds, String no, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return priceRepository.queryPrice(productIds, channelIds, no, pageable);
    }

    @Override
    public Price getById(long id) {
        return priceRepository.getOne(id);
    }

    @Override
    public Price add(Price price) throws SoftException {
        if (priceRepository.existsByNo(price.getNo())) {
            throw new SoftException(Result.ADD_FAIL, "已存在价格编码，请更换重试!");
        }
        formatPrice(price);
        if (StringUtils.isBlank(price.getNo())) {
            price.setNo(ToolUtil.timeNo());
        }
        Price price1 =  priceRepository.save(price);
        historyPriceService.add(convartHis(price1));
        return price1;
    }

    @Override
    public Price update(Price price) throws SoftException {
        if (!priceRepository.existsById(price.getId())) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        formatPrice(price);
        Price price1 =  priceRepository.save(price);
        historyPriceService.add(convartHis(price1));
        return price1;
    }

    @Override
    public void delete(Long id) throws SoftException {
        if (!priceRepository.existsById(id)) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        priceRepository.deleteById(id);
    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return priceRepository.deleteBatch(ids);
    }

    private void formatPrice(Price price) {
        if (price.getProduct() != null && price.getProduct().getId() != null) {
            price.setProduct(productRepository.getOne(price.getProduct().getId()));
        }

        if (price.getChannel() != null && price.getChannel().getId() != null) {
            price.setChannel(channelRepository.getOne(price.getChannel().getId()));
        }
    }

    private HistoryPrice convartHis(Price price) {
        HistoryPrice historyPrice = new HistoryPrice();
        BeanUtils.copyProperties(price, historyPrice);
        historyPrice.setId(null);
        return historyPrice;
    }
}
