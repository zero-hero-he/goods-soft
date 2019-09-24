package com.sw.goods.repository;

import com.sw.goods.entity.Price;
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
 * Created on 2019/9/24
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * 查询价格
     * @param productIds
     * @param channelIds
     * @param no
     * @param pageable
     * @return
     */
    @Query(value = "select * from price where 1=1 " +
            "and CASE WHEN :productId IS NOT NULL THEN PRODUCT_ID in (?1) ELSE 1=1 END " +
            "and CASE WHEN :channelId IS NOT NULL THEN CHANNEL_ID in (?2) ELSE 1=1 END " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and CASE WHEN :productId IS NOT NULL THEN PRODUCT_ID in (?1) ELSE 1=1 END " +
                    "and CASE WHEN :channelId IS NOT NULL THEN CHANNEL_ID in (?2) ELSE 1=1 END " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Price> queryPrice(List<Long> productIds, List<Long> channelIds, String no, Pageable pageable);

    boolean existsByNo(String no);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from price where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);
}
