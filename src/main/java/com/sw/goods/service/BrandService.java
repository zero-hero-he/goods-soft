package com.sw.goods.service;

import com.sw.goods.entity.Brand;
import com.sw.goods.exception.SoftException;
import org.springframework.data.domain.Page;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface BrandService {

    Page<Brand> getByName(String name, int pageNumber, int pageSize);

    Brand getById(long id);

    Brand add(Brand brand);

    Brand update(Brand brand) throws SoftException;

    void delete(Brand brand) throws SoftException;
}
