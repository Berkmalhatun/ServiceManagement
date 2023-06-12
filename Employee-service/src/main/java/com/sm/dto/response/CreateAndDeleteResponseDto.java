package com.sm.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAndDeleteResponseDto {
    private String name;
    private String surname;
    private String message;
}
