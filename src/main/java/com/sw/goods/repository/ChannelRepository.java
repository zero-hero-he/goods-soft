package com.sw.goods.repository;

import com.sw.goods.entity.Channel;
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
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query(value = "select channel.*, province.name as provinceName, city.name as cityName, country.name as countryName from channel " +
            "left join province on brand.province_id = province.province_id " +
            "left join city on brand.city_id = city.city_id " +
            "left join country on brand.country_id = country.country_id " +
            "where brand.name like concat('%',:name,'%')",
            countQuery = "select count(*) from channel where name like concat('%',:name,'%')",
            nativeQuery = true)
    Page<Channel> findByName(String name, Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from channel where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);

}
