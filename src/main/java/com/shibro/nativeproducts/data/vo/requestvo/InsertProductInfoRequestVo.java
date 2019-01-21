package com.shibro.nativeproducts.data.vo.requestvo;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;

import java.util.List;

@Data
public class InsertProductInfoRequestVo extends BaseRequestVo {
    private String name;

    private String description;

    private String type;

    private String price;

    private String saleAddress;

    private Double recommendLevel;

    private Double difficulty;

    private String mainPictureUrl;

    private List<String> otherPictureUrls;
}
