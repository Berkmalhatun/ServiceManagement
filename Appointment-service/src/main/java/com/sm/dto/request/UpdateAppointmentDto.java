package com.sm.dto.request;

import com.sm.repository.enums.EStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAppointmentDto {
    private Long id;
    private String message;

}
