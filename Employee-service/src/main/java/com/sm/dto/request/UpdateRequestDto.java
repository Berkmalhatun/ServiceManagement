package com.sm.dto.request;

import com.sm.repository.enums.EBloodGroup;
import com.sm.repository.enums.ERole;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequestDto {
    private String name;
    private String surname;
    private ERole role;
    private LocalDate starting_date; //sozlesme baslangıc tarıhı
    private LocalDate end_date; //sozlesme bıtıs tarıhı
    private String email;
    private EBloodGroup bloodGroup; // kan grubu
    private String phone_number;// telefon numarası
    private LocalDate birth_day; // doğum günü
}
