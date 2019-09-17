package com.sw.goods.service.impl;

import com.sw.goods.constent.Result;
import com.sw.goods.entity.Channel;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.*;
import com.sw.goods.service.ChannelService;
import com.sw.goods.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Page<Channel> getByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return channelRepository.findByName(name, pageable);
    }

    @Override
    public Channel getById(long id) {
        return channelRepository.getOne(id);
    }

    @Override
    public Channel add(Channel channel) {
        setArea(channel);
        channel.setNo(ToolUtil.timeNo());
        return channelRepository.save(channel);
    }

    @Override
    public Channel update(Channel channel) throws SoftException {
        if (!channelRepository.existsById(channel.getId())) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        setArea(channel);
        return channelRepository.save(channel);
    }

    @Override
    public void delete(Long id) throws SoftException {
        if (!channelRepository.existsById(id)) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        channelRepository.deleteById(id);
    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return channelRepository.deleteBatch(ids);
    }

    private void setArea(Channel channel) {
        if (channel.getProvince() != null && StringUtils.isNotBlank(channel.getProvince().getProvinceId())) {
            channel.setProvince(provinceRepository.findByProvinceId(channel.getProvince().getProvinceId()));
        } else {
            channel.setProvince(null);
        }
        if (channel.getCity() != null && StringUtils.isNotBlank(channel.getCity().getCityId())) {
            channel.setCity(cityRepository.findByCityId(channel.getCity().getCityId()));
        } else {
            channel.setCity(null);
        }
        if (channel.getCountry() != null && StringUtils.isNotBlank(channel.getCountry().getCountryId())) {
            channel.setCountry(countryRepository.findByCountryId(channel.getCountry().getCountryId()));
        } else {
            channel.setCountry(null);
        }
    }
}
