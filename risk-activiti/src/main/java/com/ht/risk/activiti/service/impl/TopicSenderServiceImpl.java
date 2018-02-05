package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.config.AmqpConfig;
import com.ht.risk.activiti.listeners.ProcessEndEventListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicSenderService")
public class TopicSenderServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEndEventListeners.class);

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void send(String context) {
        LOGGER.info("send topicExchange,message is:"+context);
        this.rabbitMessagingTemplate.convertAndSend(AmqpConfig.ACTIVITI_EXCHANGE, AmqpConfig.ACTIVITI_ROUTING_KEY, context);


    }

}
