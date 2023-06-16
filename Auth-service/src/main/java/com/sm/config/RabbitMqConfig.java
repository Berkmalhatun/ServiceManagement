package com.sm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.mailBindingKey}")
    private String mailBindingKey;
    @Value ("${rabbitmq.mailQueue}")
    private String mailQueue;
}
