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
     * @param no
     * @param pageable
     * @return
     */
    @Query(value = "select * from price where 1=1 " +
            "and PRODUCT_ID in (:productIds) " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and PRODUCT_ID in (:productIds) " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Price> queryPriceP(Long[] productIds, String no, Pageable pageable);

    @Query(value = "select * from price where 1=1 " +
            "and CHANNEL_ID in (:channelIds) " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and CHANNEL_ID in (:channelIds) " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Price> queryPriceC(Long[] channelIds, String no, Pageable pageable);

    @Query(value = "select * from price where 1=1 " +
            "and PRODUCT_ID in (:productIds) " +
            "and CHANNEL_ID in (:channelIds) " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and PRODUCT_ID in (:productIds) " +
                    "and CHANNEL_ID in (:channelIds) " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Price> queryPrice(Long[] productIds, Long[] channelIds, String no, Pageable pageable);

    @Query(value = "select * from price where 1=1 " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Price> queryPrice(String no, Pageable pageable);

    boolean existsByNo(String no);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from price where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);
}
