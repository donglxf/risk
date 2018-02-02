package com.ht.risk.activiti.rbmq;

import com.ht.risk.activiti.config.AmqpConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 摘要:
 *
 * @author xyt
 * @create 2018-01-18 下午8:28
 **/

@Component
@RabbitListener(queues = AmqpConfig.RESULT_QUEUENAME)
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}