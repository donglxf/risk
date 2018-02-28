package com.ht.risk.activiti.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class AmqpConfig {

    public final static String ACTIVITI_SELF = "activiti.self";
    public final static String ACTIVITI_SERVICE = "activiti.service";
    public final static String ACTIVITI_ROUTING_KEY = "activiti.service";
    public final static String ACTIVITI_ROUTING_ALL = "activiti.self";
    public final static String ACTIVITI_EXCHANGE = "activitiExchange";

    //创建队列
    @Bean
    public Queue queueSelf() {
        return new Queue(AmqpConfig.ACTIVITI_SELF);
    }

    //创建队列
    @Bean
    public Queue queueService() {
        return new Queue(AmqpConfig.ACTIVITI_SERVICE);
    }

    //创建交换器
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(AmqpConfig.ACTIVITI_EXCHANGE);
    }
    //对列绑定并关联到ROUTINGKEY

    @Bean
    Binding bindingExchangeMessage(Queue queueSelf, TopicExchange exchange) {
        return BindingBuilder.bind(queueSelf).to(exchange).with(AmqpConfig.ACTIVITI_ROUTING_ALL);
    }

    //对列绑定并关联到ROUTINGKEY
    @Bean
    Binding bindingExchangeMessages(Queue queueService, TopicExchange exchange) {
        return BindingBuilder.bind(queueService).to(exchange).with(AmqpConfig.ACTIVITI_ROUTING_KEY);//*表示一个词,#表示零个或多个词
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
