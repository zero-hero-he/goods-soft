package com.sw.goods.repository;

import com.sw.goods.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "select brand.*, province.name as provinceName, city.name as cityName, country.name as countryName from brand " +
            "left join province on brand.province_id = province.province_id " +
            "left join city on brand.city_id = city.city_id " +
            "left join country on brand.country_id = country.country_id " +
            "where 1=1 and CASE WHEN :name IS NOT NULL THEN brand.name LIKE CONCAT('%',:name,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from brand where 1=1 and CASE WHEN :name IS NOT NULL THEN name LIKE CONCAT('%',:name,'%') ELSE 1=1 END",
            nativeQuery = true)
    Page<Brand> findByName(String name, Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from brand where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);

    Brand getById(Long id);

}
