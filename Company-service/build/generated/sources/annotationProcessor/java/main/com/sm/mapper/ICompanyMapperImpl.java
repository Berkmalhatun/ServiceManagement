package com.sm.mapper;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.response.CreateResponseDto;
import com.sm.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T16:43:22+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toCompany(CreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.name( dto.getName() );
        company.company_address( dto.getCompany_address() );
        company.company_degree( dto.getCompany_degree() );
        company.commercial_registration_number( dto.getCommercial_registration_number() );
        company.tax_office( dto.getTax_office() );
        company.tax_identification_number( dto.getTax_identification_number() );
        company.authorized_person( dto.getAuthorized_person() );
        company.phone_number( dto.getPhone_number() );

        return company.build();
    }

    @Override
    public CreateResponseDto toCreateResponseDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CreateResponseDto.CreateResponseDtoBuilder createResponseDto = CreateResponseDto.builder();

        createResponseDto.name( company.getName() );

        createResponseDto.message( company.getName() + " company has been successfully added." );

        return createResponseDto.build();
    }
}
