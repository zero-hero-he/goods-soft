package com.sw.goods.controller;

import com.sw.goods.entity.Channel;
import com.sw.goods.exception.SoftException;
import com.sw.goods.service.ChannelService;
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
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;


    @GetMapping("/get/name")
    public HttpResult<PageResult<Channel>> getByName(String name, BaseRequestInfo baseRequestInfo) {
        return new HttpResult<>(new PageResult<>(channelService.getByName(name, baseRequestInfo.getPi(), baseRequestInfo.getPs())));
    }

    @GetMapping("/get/{id}")
    public HttpResult<Channel> get(@PathVariable @NotNull Long id) {
        return new HttpResult<>(channelService.getById(id));
    }

    @PutMapping("/add")
    public HttpResult<Channel> add(@RequestBody Channel channel) {
        return new HttpResult<>(channelService.add(channel));
    }

    @PostMapping("/update")
    public HttpResult<Channel> update(@RequestBody Channel channel) throws SoftException {
        return new HttpResult<>(channelService.update(channel));
    }

    @DeleteMapping("/delete/{id}")
    public HttpResult<Integer> delete(@PathVariable @NotNull List<Long> id) throws SoftException {
        return new HttpResult<>(channelService.delete(id));
    }

}
