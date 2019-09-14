package com.sw.goods.repository;

import com.sw.goods.entity.Batch;
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
public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query(value = "select * from batch where 1=1 " +
            "and name LIKE CONCAT('%',:name,'%')" +
            "and batch_id LIKE CONCAT('%',:batchId,'%')",
            countQuery = "select count(*) from batch where 1=1 " +
                    "and name LIKE CONCAT('%',:name,'%')" +
                    "and batch_id LIKE CONCAT('%',:batchId,'%')",
            nativeQuery = true)
    Page<Batch> findByNameLikeAndBatchIdLike(String batchId, String name, Pageable pageable);

//    Page<Batch> findByNameLikeAndBatchIdLike(String batchId, String name, Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from brand where id in (?1)", nativeQuery = true)
    int deleteBatch(List<Long> ids);

}
