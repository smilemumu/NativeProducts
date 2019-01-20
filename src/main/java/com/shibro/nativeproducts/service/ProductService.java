package com.shibro.nativeproducts.service;

import com.shibro.nativeproducts.data.dto.HomePageInfo;
import com.shibro.nativeproducts.data.entity.ProductsInfo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.persistence.ProductsInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductsInfoMapper productsInfoMapper;

    public BaseResponseVo getHomePageInfo() {
        HomePageInfo homePageInfo = new HomePageInfo();
        List<ProductsInfo> productsInfos = productsInfoMapper.selectAll();
        homePageInfo.setHomePageInfos(productsInfos);
        BaseResponseVo responseVo = BaseResponseVo.successResponseVo(homePageInfo);
        return responseVo;
    }
}
