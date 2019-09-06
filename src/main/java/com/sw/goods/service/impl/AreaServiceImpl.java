package com.sw.goods.service.impl;

import com.sw.goods.entity.City;
import com.sw.goods.entity.Country;
import com.sw.goods.entity.Province;
import com.sw.goods.repository.CityRepository;
import com.sw.goods.repository.CountryRepository;
import com.sw.goods.repository.ProvinceRepository;
import com.sw.goods.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Province getProvince(String provinceId) {
        return provinceRepository.findByProvinceId(provinceId);
    }

    @Override
    public List<City> getCitys(String provinceId) {
        return cityRepository.findByProvinceId(provinceId);
    }

    @Override
    public City getCity(String cityId) {
        return cityRepository.findByCityId(cityId);
    }

    @Override
    public List<Country> getCountrys(String cityId) {
        return countryRepository.findByCityId(cityId);
    }

    @Override
    public Country getCountry(String countryId) {
        return countryRepository.findByCountryId(countryId);
    }
}
