package com.shibro.nativeproducts.data.dto;

import com.shibro.nativeproducts.data.entity.ProductsInfo;
import lombok.Data;

import java.util.List;

@Data
public class HomePageInfo {
    private List<ProductsInfo> homePageInfos;
}
