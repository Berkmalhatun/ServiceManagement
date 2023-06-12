package com.sm.repository.entity;


import com.sm.repository.enums.EBloodGroup;
import com.sm.repository.enums.ERole;
import com.sm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String identificationNumber;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private LocalDate starting_date; //sozlesme baslangıc tarıhı
    private LocalDate end_date; //sozlesme bıtıs tarıhı
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private EBloodGroup bloodGroup; // kan grubu
    private String phone_number;// telefon numarası
    private LocalDate birth_day; // doğum günü
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;//durumu


}
