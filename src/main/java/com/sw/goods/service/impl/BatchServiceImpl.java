package com.sw.goods.service.impl;

import com.sw.goods.constent.Result;
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
        return batchRepository.getOne(id);
    }

    @Override
    public Batch add(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public Batch update(Batch batch) throws SoftException {
        if (!batchRepository.existsById(batch.getId())) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        return batchRepository.save(batch);
    }

    @Override
    public void delete(Long id) throws SoftException {
        if (!batchRepository.existsById(id)) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        batchRepository.deleteById(id);
    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return batchRepository.deleteBatch(ids);
    }
}
