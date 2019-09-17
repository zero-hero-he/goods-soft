package com.sw.goods.service;

import com.sw.goods.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/17
 */
public interface ProductService {

    Page<Product> queryProduct(Long brandId, String name, String no, String model, int pageNumber, int pageSize);

    List<Product> getByName(String name);
}
