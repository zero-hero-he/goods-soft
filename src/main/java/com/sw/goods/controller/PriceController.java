package com.sw.goods.controller;

import com.sw.goods.entity.Price;
import com.sw.goods.exception.SoftException;
import com.sw.goods.service.PriceService;
import com.sw.goods.vo.BaseRequestInfo;
import com.sw.goods.vo.HttpResult;
import com.sw.goods.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;


    @GetMapping("/get")
    public HttpResult<PageResult<Price>> get(Long[] productIds, Long[] channelIds, String no, BaseRequestInfo baseRequestInfo) {
        return new HttpResult<>(new PageResult<>(priceService.queryPrice(productIds, channelIds, no, baseRequestInfo.getPi(), baseRequestInfo.getPs())));
    }

    @GetMapping("/get/{id}")
    public HttpResult<Price> get(@PathVariable @NotNull Long id) {
        return new HttpResult<>(priceService.getById(id));
    }

    @PutMapping("/add")
    public HttpResult<Price> add(@RequestBody Price price) throws SoftException {
        return new HttpResult<>(priceService.add(price));
    }

    @PostMapping("/update")
    public HttpResult<Price> update(@RequestBody Price price) throws SoftException {
        return new HttpResult<>(priceService.update(price));
    }

    @DeleteMapping("/delete/{id}")
    public HttpResult<Integer> delete(@PathVariable @NotNull List<Long> id) throws SoftException {
        return new HttpResult<>(priceService.delete(id));
    }

}
