package com.sm.dto.request;

import com.sm.repository.enums.EStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAppointmentStatusDto {
       private Long id;
       private EStatus status;
}
