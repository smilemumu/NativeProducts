package com.shibro.nativeproducts.data.vo.requestVo;

import lombok.Data;

@Data
public class RedisSetParams {
    private String key;
    private String value;
}
