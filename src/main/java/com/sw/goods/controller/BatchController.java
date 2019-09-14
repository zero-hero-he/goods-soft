package com.sw.goods.controller;

import com.sw.goods.entity.Batch;
import com.sw.goods.exception.SoftException;
import com.sw.goods.service.BatchService;
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
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;


    @GetMapping("/get")
    public HttpResult<PageResult<Batch>> getByName(String batchId, String name, BaseRequestInfo baseRequestInfo) {
        return new HttpResult<>(new PageResult<>(batchService.get(batchId, name, baseRequestInfo.getPi(), baseRequestInfo.getPs())));
    }

    @GetMapping("/get/{id}")
    public HttpResult<Batch> get(@PathVariable @NotNull Long id) {
        return new HttpResult<>(batchService.getById(id));
    }

    @PutMapping("/add")
    public HttpResult<Batch> add(@RequestBody Batch channel) {
        return new HttpResult<>(batchService.add(channel));
    }

    @PostMapping("/update")
    public HttpResult<Batch> update(@RequestBody Batch channel) throws SoftException {
        return new HttpResult<>(batchService.update(channel));
    }

    @DeleteMapping("/delete/{id}")
    public HttpResult<Integer> delete(@PathVariable @NotNull List<Long> id) throws SoftException {
        return new HttpResult<>(batchService.delete(id));
    }

}
