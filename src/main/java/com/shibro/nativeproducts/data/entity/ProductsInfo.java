package com.shibro.nativeproducts.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductsInfo {
    private Integer id;

    private String name;

    private String description;

    private String type;

    private BigDecimal price;

    private String saleAddress;

    private Double recommendLevel;

    private Double difficulty;

    private String mainPictureUrl;

    private String otherPictureUrl;

    private Date updateTime;
}