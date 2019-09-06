package com.sw.goods.repository;

import com.sw.goods.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Country findByName(String name);

    /**
     * 根据城市ID查询
     *
     * @param cityId
     * @return
     */
    List<Country> findByCityId(String cityId);

    /**
     * 根据区ID查询
     *
     * @param countryId
     * @return
     */
    Country findByCountryId(String countryId);


}
