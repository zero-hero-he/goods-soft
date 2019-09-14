package com.sw.goods.service.impl;

import com.sw.goods.entity.Batch;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.BatchRepository;
import com.sw.goods.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/14
 */
@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;


    @Override
    public Page<Batch> get(String batchId, String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return batchRepository.findByNameLikeAndBatchIdLike(batchId, name, pageable);
    }

    @Override
    public Batch getById(long id) {
        return null;
    }

    @Override
    public Batch add(Batch batch) {
        return null;
    }

    @Override
    public Batch update(Batch batch) throws SoftException {
        return null;
    }

    @Override
    public void delete(Long id) throws SoftException {

    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return 0;
    }
}
