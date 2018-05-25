package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.config.AmqpConfig;
import com.ht.risk.activiti.listeners.ProcessEndEventListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;

@Service("topicSenderService")
public class TopicSenderServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEndEventListeners.class);

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String context) {
        LOGGER.info("mq host is :" + rabbitMessagingTemplate.getRabbitTemplate().getConnectionFactory().getHost());
        LOGGER.info("send to quene [activiti.service] message is:" + context);
        this.rabbitMessagingTemplate.convertAndSend(AmqpConfig.ACTIVITI_EXCHANGE, AmqpConfig.ACTIVITI_ROUTING_KEY, context);
        LOGGER.info("send to quene [activiti.service] message sucess");
    }

    public void sendHtApp(String context) {
        LOGGER.info("mq host is :" + rabbitMessagingTemplate.getRabbitTemplate().getConnectionFactory().getHost());
        LOGGER.info("send to quene [activiti.htapp.service] message is:" + context);
        this.amqpTemplate. convertAndSend(AmqpConfig.ACTIVITI_HTAPP_EXCHANGE, AmqpConfig.ACTIVITI_HTAPP_ROUTING_KEY, context);
//        this.amqpTemplate.send();
        LOGGER.info("send to quene [activiti.htapp.service] message sucess");
    }

    public void sendOwnerLoan(String context) {
        LOGGER.info("mq host is :" + rabbitMessagingTemplate.getRabbitTemplate().getConnectionFactory().getHost());
        LOGGER.info("send to quene [risk.model.ownerLoan] message is:" + context);
        this.rabbitMessagingTemplate.convertAndSend(AmqpConfig.ACTIVITI_OWNERLOAN_EXCHANGE, AmqpConfig.ACTIVITI_ROUTING_OWNERLOAN_KEY, context);
        LOGGER.info("send to quene [risk.model.ownerLoan] message sucess");
    }

    public void sendClsAppOwnerLoan(String context) {
        LOGGER.info("send to quene [risk.htapp.ownerLoan] message is:" + context);
        this.amqpTemplate.convertAndSend(AmqpConfig.ACTIVITI_OWNERLOAN_HTAPP_EXCHANGE, AmqpConfig.ACTIVITI_ROUTING_HTAPP_OWNERLOAN_KEY, context);
        LOGGER.info("send to quene [risk.htapp.ownerLoan] message sucess");
    }

    public void sendHtappScore(String context) {
        LOGGER.info("amqp host is :");
        LOGGER.info("send to quene [risk.hongteapp.htappscore] message is:" + context);
        this.amqpTemplate.convertAndSend(AmqpConfig.ACTIVITI_HTAPPSCORE_EXCHANGE, AmqpConfig.ACTIVITI_ROUTING_HTAPPSCORE_KEY, context);
        LOGGER.info("send to quene [risk.hongteapp.htappscore] message sucess");
    }
}
