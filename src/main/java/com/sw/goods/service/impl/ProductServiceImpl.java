package com.sw.goods.service.impl;

import com.sw.goods.constent.Result;
import com.sw.goods.entity.Product;
import com.sw.goods.exception.SoftException;
import com.sw.goods.repository.ProductRepository;
import com.sw.goods.service.BrandService;
import com.sw.goods.service.ProductService;
import com.sw.goods.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private BrandService brandService;

    @Override
    public Page<Product> queryProduct(Long brandId, String name, String no, String model, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepository.queryProduct(brandId, name, no, model, pageable);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findByNameIsLike(name);
    }

    @Override
    public Product getById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product add(Product product) {
        product.setBrand(brandService.getById(product.getBrand().getId()));
        if (StringUtils.isBlank(product.getNo())) {
            product.setNo(ToolUtil.timeNo());
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) throws SoftException {
        if(!productRepository.existsById(product.getId())) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        product.setBrand(brandService.getById(product.getBrand().getId()));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) throws SoftException {
        if (!productRepository.existsById(id)) {
            throw new SoftException(Result.UPDATE_FAIL, "不存在这条记录!");
        }
        productRepository.deleteById(id);
    }

    @Override
    public int delete(List<Long> ids) throws SoftException {
        return productRepository.deleteBatch(ids);
    }

}
