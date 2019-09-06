package com.sw.goods.service.impl;

import com.sw.goods.entity.Brand;
import com.sw.goods.repository.BrandRepository;
import com.sw.goods.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<Brand> getByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber,pageSize);
        return brandRepository.findByName(name, pageable);
    }
}
