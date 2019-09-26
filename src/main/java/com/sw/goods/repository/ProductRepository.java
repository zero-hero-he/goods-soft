package com.sw.goods.repository;

import com.sw.goods.entity.Product;
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
public interface ProductRepository extends JpaRepository<Product, Long> {


    /**
     *
     * @param brandId 品牌
     * @param name 名称
     * @param no 编号
     * @param model 型号
     * @param pageable
     * @return
     */
    @Query(value = "select * from product where 1=1 " +
            "and CASE WHEN :brandId IS NOT NULL THEN brand_id = :brandId ELSE 1=1 END " +
            "and CASE WHEN :name IS NOT NULL THEN name LIKE CONCAT('%',:name,'%') ELSE 1=1 END " +
            "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END " +
            "and CASE WHEN :model IS NOT NULL THEN model LIKE CONCAT('%',:model,'%') ELSE 1=1 END ",
            countQuery = "select count(*) from product where 1=1 " +
                    "and CASE WHEN :brandId IS NOT NULL THEN brand_id = :brandId ELSE 1=1 END " +
                    "and CASE WHEN :name IS NOT NULL THEN name LIKE CONCAT('%',:name,'%') ELSE 1=1 END " +
                    "and CASE WHEN :no IS NOT NULL THEN `no` LIKE CONCAT('%',:no,'%') ELSE 1=1 END " +
                    "and CASE WHEN :model IS NOT NULL THEN model LIKE CONCAT('%',:model,'%') ELSE 1=1 END ",
            nativeQuery = true)
    Page<Product> queryProduct(Long brandId, String name, String no, String model, Pageable pageable);

    @Query(value = "select * from product where 1=1 " +
            "and CASE WHEN :name IS NOT NULL THEN name LIKE CONCAT('%',:name,'%') ELSE 1=1 END ",
            nativeQuery = true)
    List<Product> findByNameIsLike(String name);

    boolean existsByNo(String no);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from product where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);

}
