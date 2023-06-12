package com.sm.repository.entity;

import com.sm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Company extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //firma ismi
    private String company_address; //firma adresi
    private String company_degree; //firma ünvanı
    private String commercial_registration_number; //ticari sicil numarası
    private String tax_office;//vergi dairesi
    private Long tax_identification_number; //vergi numarası
    private String authorized_person;//yetkili kişi
    private String phone_number;//telefon numarası
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus eStatus=EStatus.ACTIVE;
}
