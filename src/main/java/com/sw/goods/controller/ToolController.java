package com.sw.goods.controller;

import com.sw.goods.constent.Result;
import com.sw.goods.util.ToolUtil;
import com.sw.goods.vo.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2019/9/21
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @GetMapping("/no")
    public HttpResult<String> getByName() {
        return new HttpResult<>(Result.SUCCESS_CODE, "生成完成", ToolUtil.timeNo());
    }
}
