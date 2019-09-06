package com.sw.goods.repository;

import com.sw.goods.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    City findByName(String name);

    /**
     * 根据城市ID查询
     *
     * @param cityId
     * @return
     */
    City findByCityId(String cityId);

    /**
     * 根据省份ID查询
     *
     * @param provinceId
     * @return
     */
    List<City> findByProvinceId(String provinceId);


}
