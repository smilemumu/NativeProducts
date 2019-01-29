package com.shibro.nativeproducts.data.enums;

import com.shibro.nativeproducts.data.dto.ProductTypeItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductTypeEnum {
    CRAFTS(1,"工艺品"),
    DRINK(2,"饮料"),
    CONDIMENT(3,"调味品"),
    TEA(4,"茶叶"),
    GRAIN_AND_OIL(5,"粮油"),
    FRUIT_AND_VEGETABL(6,"水果蔬菜"),
    OTHERFOOD(7,"副食品"),
    ALGAE(8,"食用菌藻"),
    BIRDS(9,"畜牧特禽"),
    AQUATIC_PRODUCTS(10,"水产海货"),
    OTHER(11,"其他"),
    ;

    private Integer code;
    private String desc;

    public static List<ProductTypeItem> getProductType(){
        return Arrays.stream(ProductTypeEnum.values()).map(i->{
           ProductTypeItem item = new ProductTypeItem();
           item.setType(i.getCode());
           item.setName(i.getDesc());
           return item;
        }).collect(Collectors.toList());
    }

    ProductTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getByType(String type) {
        return Arrays.stream(ProductTypeEnum.values()).filter(i->i.getCode().equals(Integer.parseInt(type)))
                .map(i->i.getDesc()).findFirst().orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
