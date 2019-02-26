/*
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 * Author：zhengyinghai
 * Date：2014-07-29
 * Description: queue sender for ris
 */
package com.shibro.nativeproducts.utils.amq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author
 */
public class AmqQueueSender {

    private static final Logger LOG = LoggerFactory.getLogger(AmqQueueSender.class);

    private JmsTemplate jmsTemplate;

    /**
     * 发送消息
     *
     * @param message :
     * @throws InterruptedException :
     */
    public void send(String message, final String amqDestinationName) throws InterruptedException {

        this.jmsTemplate.send(amqDestinationName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) {
                Message msg = null;
                try {
                    msg = session.createTextMessage(message);
                    return msg;
                } catch (JMSException e) {
                    LOG.error("create text messsage failed, message=" + message + ", destination=" + amqDestinationName
                            + ", error=" + e.getMessage(), e);
                    return msg;
                }
            }
        });
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
