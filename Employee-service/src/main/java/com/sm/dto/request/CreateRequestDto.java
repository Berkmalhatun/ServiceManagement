package com.sm.dto.request;

import com.sm.repository.enums.ERole;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateRequestDto {
    private String name;
    private String surname;
    private String identificationNumber;
    private ERole role;
    private LocalDate starting_date; //sozlesme baslangıc tarıhı
    private LocalDate end_date; //sozlesme bıtıs tarıhı
    private String email;
    private String phone_number;// telefon numarası
    private LocalDate birth_day; // doğum günü
}
