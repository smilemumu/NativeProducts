package com.shibro.nativeproducts.persistence;


import com.shibro.nativeproducts.data.entity.ProductsInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ProductsInfo record);

    ProductsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductsInfo record);

    List<ProductsInfo> selectAll();
}