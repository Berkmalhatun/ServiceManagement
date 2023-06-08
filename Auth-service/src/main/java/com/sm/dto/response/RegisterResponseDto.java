package com.sm.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterResponseDto {
    private String email;
    private String message;
}
