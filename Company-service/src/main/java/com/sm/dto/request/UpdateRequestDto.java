package com.sm.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequestDto {
    private String name; //firma ismi
    private String company_address; //firma adresi
    private String company_degree; //firma ünvanı
    private String commercial_registration_number; //ticari sicil numarası
    private String tax_office;//vergi dairesi
    private Long tax_identification_number; //vergi numarası
    private String authorized_person;//yetkili kişi
    private String phone_number;//telefon numarası

}
