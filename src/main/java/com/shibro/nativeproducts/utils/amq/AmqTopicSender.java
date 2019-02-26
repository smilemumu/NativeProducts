package com.shibro.nativeproducts.utils.amq;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Topic消息
 *
 * @author
 */
public class AmqTopicSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqTopicSender.class);

    @Resource
    private JmsTemplate jmsTopicTemplate;

    @Value("${lsp.lap.topic.feedback}")
    private String messageQueue;

    /**
     * 发送消息
     */
    public void sendTxtMsg(final String message) {
        LOGGER.info("消息队列:[{}]，消息入参：{}", messageQueue, message);
        ActiveMQTopic queueDestination = new ActiveMQTopic(messageQueue);
        try {
            jmsTopicTemplate.send(queueDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
            LOGGER.info("消息队列:[{}]，push消息成功", messageQueue);
        } catch (Exception e) {
            LOGGER.error("消息队列:[{}], push消息异常:", messageQueue, e);
        }
    }
}
