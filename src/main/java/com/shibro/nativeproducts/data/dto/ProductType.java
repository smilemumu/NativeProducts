package com.shibro.nativeproducts.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductType {
    private List<ProductTypeItem> types;
}
