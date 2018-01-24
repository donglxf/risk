package com.ht.risk.activiti.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Created by Administrator on 2016/6/21.
 */
@Configuration
public class AmqpConfig implements RabbitListenerConfigurer {


    public static final String RESULT_EXCHANGE = "activti-result-exchange";
    public static final String RESULT_QUEUENAME = "activiti-result-queue";


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("10.110.1.240:5672");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    Queue queue(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RESULT_QUEUENAME,true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

//    @Bean
//    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
//        TopicExchange topicExchange = new TopicExchange("exchange");
//        rabbitAdmin.declareExchange(topicExchange);
//        return topicExchange;
//    }
    @Bean
    FanoutExchange exchange(RabbitAdmin rabbitAdmin){
        FanoutExchange fanoutExchange = new FanoutExchange(RESULT_EXCHANGE);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    @Bean
    Binding binding(Queue queue,FanoutExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // factory.setPrefetchCount(5);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }
}
