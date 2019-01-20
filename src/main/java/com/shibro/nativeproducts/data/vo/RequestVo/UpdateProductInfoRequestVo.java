package com.shibro.nativeproducts.data.vo.RequestVo;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateProductInfoRequestVo extends BaseRequestVo {
    private Integer id;

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
