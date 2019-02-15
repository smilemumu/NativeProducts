package com.shibro.nativeproducts.data.vo.responseVo;

import lombok.Data;

@Data
public class BaseTalkResponseData<T extends BaseTalkResponseDetail> {
    private String name;
    private T details;

    public BaseTalkResponseData() {
    }

    public BaseTalkResponseData(T details) {
        this.details = details;
    }

    public BaseTalkResponseData(String name, T details) {
        this.name = name;
        this.details = details;
    }
}
