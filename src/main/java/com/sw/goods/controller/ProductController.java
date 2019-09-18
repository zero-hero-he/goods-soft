package com.sw.goods.controller;

import com.sw.goods.entity.Product;
import com.sw.goods.exception.SoftException;
import com.sw.goods.service.ProductService;
import com.sw.goods.vo.BaseRequestInfo;
import com.sw.goods.vo.HttpResult;
import com.sw.goods.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/get")
    public HttpResult<PageResult<Product>> getByName(Long brandId, String name, String no, String model, BaseRequestInfo baseRequestInfo) {
        return new HttpResult<>(new PageResult<>(productService.queryProduct(brandId, name, no, model, baseRequestInfo.getPi(), baseRequestInfo.getPs())));
    }

    @GetMapping("/name")
    public HttpResult<List<Product>> get(String name) {
        return new HttpResult<>(productService.getByName("%" + name + "%"));
    }

    @GetMapping("/get/{id}")
    public HttpResult<Product> get(@PathVariable @NotNull Long id) {
        return new HttpResult<>(productService.getById(id));
    }

    @PutMapping("/add")
    public HttpResult<Product> add(@RequestBody Product product) {
        return new HttpResult<>(productService.add(product));
    }

    @PostMapping("/update")
    public HttpResult<Product> update(@RequestBody Product product) throws SoftException {
        return new HttpResult<>(productService.update(product));
    }

    @DeleteMapping("/delete/{id}")
    public HttpResult<Integer> delete(@PathVariable @NotNull List<Long> id) throws SoftException {
        return new HttpResult<>(productService.delete(id));
    }

}
