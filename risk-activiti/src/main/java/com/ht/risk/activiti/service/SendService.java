package com.ht.risk.activiti.service;

import com.ht.risk.activiti.config.AmqpConfig;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SendService {

    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendToRabbitmq(String message){
        System.out.println("sendToRabbitmq="+message);
        this.rabbitMessagingTemplate.convertAndSend(AmqpConfig.RESULT_EXCHANGE, AmqpConfig.RESULT_ROUTINGKEY,message);
    }

}
