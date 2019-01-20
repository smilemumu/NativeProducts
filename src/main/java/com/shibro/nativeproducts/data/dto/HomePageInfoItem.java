package com.shibro.nativeproducts.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HomePageInfoItem {
    private String name;

    private String description;

    private String type;

    private BigDecimal price;

    private String saleAddress;

    private Double recommendLevel;

    private Double difficulty;

    private String mainPictureUrl;

    private List<String> otherPictureUrls;
}
