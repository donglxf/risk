package com.ht.risk.activiti.rbmq;

import com.ht.risk.activiti.config.AmqpConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 摘要:
 *
 * @author xyt
 * @create 2018-01-18 下午8:28
 **/

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(AmqpConfig.RESULT_QUEUENAME, context);
    }

}