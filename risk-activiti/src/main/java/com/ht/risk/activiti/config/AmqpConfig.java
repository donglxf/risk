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

    public static final String RESULT_EXCHANGE = "activti-result-exchange";
    public static final String RESULT_ROUTINGKEY = "activti-result-routingKey";
    public static final String RESULT_QUEUENAME = "activiti-result-queue";

    public static final String RESULT_QUEUENAME2 = "activiti-result-queue2";


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        //connectionFactory.setAddresses("10.110.1.240:5672");
        connectionFactory.setAddresses("127.0.0.1:5672");
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

    @Bean
    Queue queue2(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue("hello",true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

   /* @Bean
    TopicExchange topicExchange(RabbitAdmin rabbitAdmin){
        TopicExchange topicExchange = new TopicExchange(RESULT_EXCHANGE);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }
    @Bean
    Binding binding(Queue queue,TopicExchange topicExchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RESULT_ROUTINGKEY);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }*/

    @Bean
    FanoutExchange fanoutExchange(RabbitAdmin rabbitAdmin){
        FanoutExchange fanoutExchange = new FanoutExchange(RESULT_EXCHANGE);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    @Bean
    Binding binding(Queue queue,FanoutExchange fanoutExchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
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
