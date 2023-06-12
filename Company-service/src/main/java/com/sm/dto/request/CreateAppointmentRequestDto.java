package com.sm.dto.request;

import com.sm.repository.entity.BaseEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAppointmentRequestDto {
        private Long companyId;
        private String message;
}
