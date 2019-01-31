package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestvo.DeleteProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.requestvo.InsertProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.requestvo.UpdateProductInfoRequestVo;
import com.shibro.nativeproducts.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController {

//    http://down-www.newasp.net/pcdown/soft/soft1/intellij.idea.2018.ult.rar
//    idea下载地址

    @Resource
    private ProductService productService;

    /**
     * 首页信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/product/getHomePageInfo", method = RequestMethod.POST)
    public BaseResponseVo getHomePageInfo(@RequestBody BaseRequestVo requestVo){
        return productService.getHomePageInfo();
    }


    /**
     * 新增土特产信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/product/insertProductInfo", method = RequestMethod.POST)
    public BaseResponseVo insertProductInfo(@RequestBody InsertProductInfoRequestVo requestVo){
        return productService.insertProductInfo(requestVo);
    }

    /**
     * 删除土特产信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/product/deleteProductInfo", method = RequestMethod.POST)
    public BaseResponseVo deleteProductInfo(@RequestBody DeleteProductInfoRequestVo requestVo){
        return productService.deleteProductInfo(requestVo);
    }


    /**
     * 更新土特产信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/product/updateProductInfo", method = RequestMethod.POST)
    public BaseResponseVo updateProductInfo(@RequestBody UpdateProductInfoRequestVo requestVo){
        return productService.updateProductInfo(requestVo);
    }

    /**
     * 土特产类别信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/product/queryProductType", method = RequestMethod.POST)
    public BaseResponseVo updateProductInfo(@RequestBody BaseRequestVo requestVo){
        return productService.queryProductType(requestVo);
    }
}
