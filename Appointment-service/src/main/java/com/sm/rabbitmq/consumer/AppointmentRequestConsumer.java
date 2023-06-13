package com.sm.rabbitmq.consumer;

import com.sm.rabbitmq.model.AppointmentRequestModel;
import com.sm.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentRequestConsumer {
    private final AppointmentService appointmentService;
    @RabbitListener(queues = "${rabbitmq.queueAppointmentRequest}")
    public void sendMail(AppointmentRequestModel model){
        log.info("Model {}", model.toString());
        appointmentService.createAppointmentWithRabbitMq(model);
    }
}
