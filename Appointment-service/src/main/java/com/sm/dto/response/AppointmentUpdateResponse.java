package com.sm.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentUpdateResponse {
    private String message;
    private String process;// yapılan işlem. örnek:"Acıklama basarıyla degıstırıldı.
}
