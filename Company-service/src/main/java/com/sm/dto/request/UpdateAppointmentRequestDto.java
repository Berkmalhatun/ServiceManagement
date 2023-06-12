package com.sm.dto.request;

import com.sm.repository.enums.EStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAppointmentRequestDto {
    private Long id;
    private String message;
}
