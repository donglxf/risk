package com.ht.risk.activiti.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.annotation.Resource;

@Configuration
public class AmqpConfig {

    public final static String ACTIVITI_SERVICE = "activiti.service";
    public final static String ACTIVITI_ROUTING_KEY = "activiti.service";
    public final static String ACTIVITI_EXCHANGE = "activitiExchange";

    public final static String ACTIVITI_SERVICE_OWNERLOAN = "risk.model.ownerLoan";
    public final static String ACTIVITI_ROUTING_OWNERLOAN_KEY = "risk.model.ownerLoan";
    public final static String ACTIVITI_OWNERLOAN_EXCHANGE = "risk.model.ownerLoan";

    @Value("${spring.rabbitmq.host:}")
    private String host;
    @Value("${spring.rabbitmq.port:}")
    private int port;
    @Value("${spring.rabbitmq.username:}")
    private String username;
    @Value("${spring.rabbitmq.password:}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }



    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    //创建队列
    @Bean
    public Queue queueService(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(AmqpConfig.ACTIVITI_SERVICE);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    //创建队列
    @Bean
    public Queue queueOwnerLoan(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(AmqpConfig.ACTIVITI_SERVICE_OWNERLOAN);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    //创建交换器
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(AmqpConfig.ACTIVITI_EXCHANGE,true,false);
    }

    @Bean
    DirectExchange exchangeOwnerLoan() {
        return new DirectExchange(AmqpConfig.ACTIVITI_OWNERLOAN_EXCHANGE,true,false);
    }


    //对列绑定并关联到ROUTINGKEY
    @Bean
    Binding bindingExchangeMessages(Queue queueService, DirectExchange exchange) {
        return BindingBuilder.bind(queueService).to(exchange).with(AmqpConfig.ACTIVITI_ROUTING_KEY);//*表示一个词,#表示零个或多个词
    }

    @Bean
    Binding bindingExchangeOwnerLoan(Queue queueOwnerLoan, DirectExchange exchangeOwnerLoan) {
        return BindingBuilder.bind(queueOwnerLoan).to(exchangeOwnerLoan).with(AmqpConfig.ACTIVITI_ROUTING_OWNERLOAN_KEY);//*表示一个词,#表示零个或多个词
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
