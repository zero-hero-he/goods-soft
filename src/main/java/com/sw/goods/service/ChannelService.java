package com.sw.goods.service;

import com.sw.goods.entity.Channel;
import com.sw.goods.exception.SoftException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface ChannelService {

    Page<Channel> getByName(String name, int pageNumber, int pageSize);

    Channel getById(long id);

    Channel add(Channel channel);

    Channel update(Channel channel) throws SoftException;

    void delete(Long id) throws SoftException;

    int delete(List<Long> ids) throws SoftException;
}
