package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController {


    @Resource
    private ProductService productService;

    @RequestMapping(value = "/product/getHomePageInfo", method = RequestMethod.POST)
    public BaseResponseVo getHomePageInfo(@RequestBody BaseRequestVo requestVo){
        return productService.getHomePageInfo();
    }

}
