package com.sm.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestModel implements Serializable {
    private Long id;
    private String message;
//burada ne gonderecegımızı belırtırız.
}
