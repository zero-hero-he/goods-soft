package com.sw.goods.repository;

import com.sw.goods.entity.HistoryPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/24
 */
public interface HistoryPriceRepository extends JpaRepository<HistoryPrice, Long> {

    /**
     * 查询价格
     *
     * @param productIds
     * @param channelIds
     * @param no
     * @param pageable
     * @return
     */
    @Query(value = "select * from history_price where 1=1 " +
            "and CASE WHEN :productIds IS NOT NULL THEN PRODUCT_ID in (?1) ELSE 1=1 END " +
            "and CASE WHEN :channelIds IS NOT NULL THEN CHANNEL_ID in (?2) ELSE 1=1 END " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and CASE WHEN :productIds IS NOT NULL THEN PRODUCT_ID in (?1) ELSE 1=1 END " +
                    "and CASE WHEN :channelIds IS NOT NULL THEN CHANNEL_ID in (?2) ELSE 1=1 END " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<HistoryPrice> queryPrice(List<Long> productIds, List<Long> channelIds, String no, Pageable pageable);
}
