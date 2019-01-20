package com.shibro.nativeproducts.data.vo.RequestVo;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InsertProductInfoRequestVo extends BaseRequestVo {
    private String description;

    private String type;

    private BigDecimal price;

    private String saleAddress;

    private Double recommendLevel;

    private Double difficulty;

    private String mainPictureUrl;
}
