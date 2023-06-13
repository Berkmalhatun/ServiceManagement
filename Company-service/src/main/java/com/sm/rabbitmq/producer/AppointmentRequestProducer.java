package com.sm.rabbitmq.producer;

import com.sm.rabbitmq.model.AppointmentRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//burası gonderen methoddr
@Service
@RequiredArgsConstructor
public class AppointmentRequestProducer {
    @Value("${rabbitmq.exchange-company}")
    private String exchange;
    @Value("${rabbitmq.appointmentRequestBindingKey}")
    private String appointmentRequestBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendNewAppointmentRequest(AppointmentRequestModel model){
        rabbitTemplate.convertAndSend(exchange,appointmentRequestBindingKey,model);
    }
}
