package com.sm.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.mailQueue}")
    private String mailQueue;
    @Bean
    Queue mailQueue(){
        return new Queue(mailQueue);
    }
}
