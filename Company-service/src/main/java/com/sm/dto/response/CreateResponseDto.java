package com.sm.dto.response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateResponseDto {
    private String name;
    private String message;
}
