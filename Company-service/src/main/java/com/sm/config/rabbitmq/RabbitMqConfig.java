package com.sm.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-company}")
    private String exchange;
    @Value("${rabbitmq.appointmentRequestBindingKey}")
    private String appointmentRequestBindingKey;
    @Value("${rabbitmq.queueAppointmentRequest}")
    private String appointmentRequestQueue;

    @Bean
    DirectExchange exchangeCompanyAndAppointmentRequest(){
        return  new DirectExchange(exchange);
    }
    @Bean
    Queue appointmentRequestQueue(){
        return new Queue(appointmentRequestQueue);
    }

    @Bean
    public Binding appointmentRequestBindingKey(final Queue appointmentRequestQueue,final DirectExchange exchange){
        return BindingBuilder.bind(appointmentRequestQueue).to(exchange).with(appointmentRequestBindingKey);
    }

}
