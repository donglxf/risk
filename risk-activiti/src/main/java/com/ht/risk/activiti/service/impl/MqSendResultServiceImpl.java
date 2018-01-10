package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.service.MqSendResultService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MqSendResultServiceImpl implements MqSendResultService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }
}
