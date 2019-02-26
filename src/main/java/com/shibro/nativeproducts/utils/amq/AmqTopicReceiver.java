package com.shibro.nativeproducts.utils.amq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author sheffieldhuang
 */
public class AmqTopicReceiver implements MessageListener {

    private static Logger LOGGER = LoggerFactory.getLogger(AmqTopicReceiver.class);

    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            LOGGER.info("recv topic message", textMessage.getText());
            //TODO  转换成Bean 然后做逻辑处理

        } catch (JMSException e) {
            LOGGER.error("onMessage", e);
        }
    }
}
