package com.shibro.nativeproducts.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomePageInfo {
    private Integer count;
    private List<HomePageInfoItem> homePageInfos;
}
