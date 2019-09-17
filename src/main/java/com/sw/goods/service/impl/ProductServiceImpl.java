package com.sw.goods.service.impl;

import com.sw.goods.entity.Product;
import com.sw.goods.repository.ProductRepository;
import com.sw.goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/17
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> queryProduct(Long brandId, String name, String no, String model, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepository.queryProduct(brandId, name, no, model, pageable);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findByNameIsLike(name);
    }
}
