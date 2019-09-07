package com.sw.goods.controller;

import com.sw.goods.entity.Brand;
import com.sw.goods.service.BrandService;
import com.sw.goods.vo.HttpResult;
import com.sw.goods.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/7
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("/get/name")
    public HttpResult<PageResult<Brand>> getByName(String name, int pageNumber, int pageSize) {
        return new HttpResult<>(new PageResult<>(brandService.getByName(name, pageNumber, pageSize)));
    }

    @GetMapping("/get/{id}")
    public HttpResult<Brand> get(@PathVariable Long id) {
        return new HttpResult<>(brandService.getById(id));
    }

    @PostMapping("/add")
    public HttpResult<Brand> add(@RequestBody Brand brand) {
        return new HttpResult<>(brandService.add(brand));
    }

    @PostMapping("/update")
    public HttpResult<Brand> update(@RequestBody Brand brand) {
        return new HttpResult<>(brandService.update(brand));
    }

}
