package com.sm.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateEmailRequestDto {
    private String identificationNumber;
    private String email;
}
