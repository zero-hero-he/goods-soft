package com.sw.goods.service.impl;

import com.sw.goods.constent.Result;
import com.sw.goods.entity.Brand;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.BrandRepository;
import com.sw.goods.repository.CityRepository;
import com.sw.goods.repository.CountryRepository;
import com.sw.goods.repository.ProvinceRepository;
import com.sw.goods.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Page<Brand> getByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return brandRepository.findByName(name, pageable);
    }

    @Override
    public Brand getById(long id) {
        return brandRepository.getOne(id);
    }

    @Override
    public Brand add(Brand brand) {
        setArea(brand);
        brand.setNo(ToolUtil.timeNo());
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) throws SoftException {
        if (!brandRepository.existsById(brand.getId())) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        setArea(brand);
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) throws SoftException {
        if (!brandRepository.existsById(id)) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        brandRepository.deleteById(id);
    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return brandRepository.deleteBatch(ids);
    }

    private void setArea(Brand brand) {
        if (brand.getProvince() != null && StringUtils.isNotBlank(brand.getProvince().getProvinceId())) {
            brand.setProvince(provinceRepository.findByProvinceId(brand.getProvince().getProvinceId()));
        } else {
            brand.setProvince(null);
        }
        if (brand.getCity() != null && StringUtils.isNotBlank(brand.getCity().getCityId())) {
            brand.setCity(cityRepository.findByCityId(brand.getCity().getCityId()));
        } else {
            brand.setCity(null);
        }
        if (brand.getCountry() != null && StringUtils.isNotBlank(brand.getCountry().getCountryId())) {
            brand.setCountry(countryRepository.findByCountryId(brand.getCountry().getCountryId()));
        } else {
            brand.setCountry(null);
        }
    }
}
