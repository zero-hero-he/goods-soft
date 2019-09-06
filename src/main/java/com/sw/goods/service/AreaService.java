package com.sw.goods.service;

import com.sw.goods.entity.City;
import com.sw.goods.entity.Country;
import com.sw.goods.entity.Province;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface AreaService {

    List<Province> getAllProvinces();

    Province getProvince(String provinceId);

    List<City> getCitys(String provinceId);

    City getCity(String cityId);

    List<Country> getCountrys(String cityId);

    Country getCountry(String countryId);

}
