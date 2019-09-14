package com.sw.goods.service;

import com.sw.goods.entity.Batch;
import com.sw.goods.exception.SoftException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/14
 */
public interface BatchService {

    Page<Batch> get(String batchId, String name, int pageNumber, int pageSize);

    Batch getById(long id);

    Batch add(Batch batch);

    Batch update(Batch batch) throws SoftException;

    void delete(Long id) throws SoftException;

    int delete(List<Long> ids) throws SoftException;
}
