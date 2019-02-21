package com.shibro.nativeproducts.listener;

import com.alibaba.fastjson.JSON;
import com.shibro.nativeproducts.data.kafka.KafkaDemoMessage;
import com.shibro.nativeproducts.filter.MdcFilter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.listener.MessageListener;

import java.util.UUID;

public class KafkaDemoMessageListener implements MessageListener<String, String> {

    private final static Logger LOG = LoggerFactory.getLogger(KafkaDemoMessageListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        try {
            MDC.put(MdcFilter.MDC_ID, UUID.randomUUID().toString());
            String value = data.value();
            LOG.info("[kafka.bootstrap.servers topic] -> {}", value);
            KafkaDemoMessage demoMessage = JSON.parseObject(value,KafkaDemoMessage.class);
            if (null != demoMessage &&demoMessage.getKey().equals("demo")) {
               //进一步处理
            }
        } catch (Exception e) {
            LOG.error("kafka.bootstrap.servers topic信息处理失败，系统异常", e);
        } finally {
            MDC.remove(MdcFilter.MDC_ID);
        }
    }
}
