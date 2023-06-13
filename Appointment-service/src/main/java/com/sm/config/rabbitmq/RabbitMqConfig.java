package com.sm.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queueAppointmentRequest}")
    private String appointmentRequestQueue;

    @Bean
    Queue appointmentRequestQueue(){
        return new Queue(appointmentRequestQueue);
    }

}
