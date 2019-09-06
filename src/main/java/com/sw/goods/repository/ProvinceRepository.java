package com.sw.goods.repository;

import com.sw.goods.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Province findByName(String name);

    /**
     * 根据省份ID查询
     *
     * @param provinceId
     * @return
     */
    Province findByProvinceId(String provinceId);
}
