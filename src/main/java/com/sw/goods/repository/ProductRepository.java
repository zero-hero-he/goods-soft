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
            "and IF(:brandId IS NOT NULL ,brand_id = :brandId ,1 = 1 ) " +
            "and IF(:name IS NOT NULL ,name LIKE CONCAT('%',:name,'%') ,1 = 1 ) " +
            "and IF(:no IS NOT NULL ,`no` LIKE CONCAT('%',:no,'%') ,1 = 1 ) " +
            "and IF(:model IS NOT NULL ,model LIKE CONCAT('%',:model,'%') ,1 = 1 )",
            countQuery = "select count(*) from product where 1=1 " +
                    "and IF(:brandId IS NOT NULL ,brand_id = :brandId ,1 = 1 ) " +
                    "and IF(:name IS NOT NULL ,name LIKE CONCAT('%',:name,'%') ,1 = 1 ) " +
                    "and IF(:no IS NOT NULL ,`no` LIKE CONCAT('%',:no,'%') ,1 = 1 ) " +
                    "and IF(:model IS NOT NULL ,model LIKE CONCAT('%',:model,'%') ,1 = 1 )",
            nativeQuery = true)
    Page<Product> queryProduct(Long brandId, String name, String no, String model, Pageable pageable);

    List<Product> findByNameIsLike(String name);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from product where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);

}
