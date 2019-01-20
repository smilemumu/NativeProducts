package com.shibro.nativeproducts.data.enums;

public enum ProductTypeEnum {
    CRAFTS(1,"工艺品"),
    DRINK(2,"饮料"),
    CONDIMENT(3,"调味品"),
    ;
    private Integer code;
    private String desc;

    ProductTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
