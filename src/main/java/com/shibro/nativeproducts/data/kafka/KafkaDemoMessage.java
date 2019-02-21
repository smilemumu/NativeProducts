package com.shibro.nativeproducts.data.kafka;

import lombok.Data;

@Data
public class KafkaDemoMessage {
    private String key;
    private String value;
}
