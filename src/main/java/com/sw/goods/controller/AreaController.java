package com.sw.goods.controller;

import com.sw.goods.entity.City;
import com.sw.goods.entity.Country;
import com.sw.goods.entity.Province;
import com.sw.goods.service.AreaService;
import com.sw.goods.vo.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/9
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/province")
    public HttpResult<List<Province>> getProvinces() {
        return new HttpResult<>(areaService.getAllProvinces());
    }

    @GetMapping("/province/{provinceId}")
    public HttpResult<Province> getProvince(@PathVariable String provinceId) {
        return new HttpResult<>(areaService.getProvince(provinceId));
    }

    @GetMapping("/citys/{provinceId}")
    public HttpResult<List<City>> getCitys(@PathVariable String provinceId) {
        return new HttpResult<>(areaService.getCitys(provinceId));
    }

    @GetMapping("/city/{cityId}")
    public HttpResult<City> getCity(@PathVariable String cityId) {
        return new HttpResult<>(areaService.getCity(cityId));
    }

    @GetMapping("/countrys/{cityId}")
    public HttpResult<List<Country>> getCountrys(@PathVariable String cityId) {
        return new HttpResult<>(areaService.getCountrys(cityId));
    }

    @GetMapping("/country/{countryId}")
    public HttpResult<Country> getCountry(@PathVariable String countryId) {
        return new HttpResult<>(areaService.getCountry(countryId));
    }


}
