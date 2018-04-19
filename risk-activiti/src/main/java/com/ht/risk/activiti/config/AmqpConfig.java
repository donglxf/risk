package com.ht.risk.activiti.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class AmqpConfig {

    public final static String ACTIVITI_SERVICE = "activiti.service";
    public final static String ACTIVITI_ROUTING_KEY = "activiti.service";
    public final static String ACTIVITI_EXCHANGE = "activitiExchange";

    public final static String ACTIVITI_SERVICE_OWNERLOAN = "risk.model.ownerLoan";
    public final static String ACTIVITI_ROUTING__OWNERLOAN_KEY = "risk.model.ownerLoan";
    public final static String ACTIVITI_OWNERLOAN_EXCHANGE = "risk.model.ownerLoan";


    //创建队列
    @Bean
    public Queue queueService() {
        return new Queue(AmqpConfig.ACTIVITI_SERVICE);
    }

    //创建队列
    @Bean
    public Queue queueOwnerLoan() {
        return new Queue(AmqpConfig.ACTIVITI_SERVICE_OWNERLOAN);
    }


    //创建交换器
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(AmqpConfig.ACTIVITI_EXCHANGE);
    }

    @Bean
    TopicExchange exchangeOwnerLoan() {
        return new TopicExchange(AmqpConfig.ACTIVITI_OWNERLOAN_EXCHANGE);
    }


    //对列绑定并关联到ROUTINGKEY
    @Bean
    Binding bindingExchangeMessages(Queue queueService, TopicExchange exchange) {
        return BindingBuilder.bind(queueService).to(exchange).with(AmqpConfig.ACTIVITI_ROUTING_KEY);//*表示一个词,#表示零个或多个词
    }

    @Bean
    Binding bindingExchangeOwnerLoan(Queue queueOwnerLoan, TopicExchange exchangeOwnerLoan) {
        return BindingBuilder.bind(queueOwnerLoan).to(exchangeOwnerLoan).with(AmqpConfig.ACTIVITI_ROUTING__OWNERLOAN_KEY);//*表示一个词,#表示零个或多个词
    }


    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    /**
     * 生产者用
     * @return
     */
    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }


}
