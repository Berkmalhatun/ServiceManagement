package com.sm.repository.entity;

import com.sm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Appointment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appointmentRequestId;
   // private Long employeeId;
    private String message;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PASSIVE;
}
