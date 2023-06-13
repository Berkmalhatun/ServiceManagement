package com.sm.mapper;

import com.sm.dto.request.CreateAppointmentRequestDto;
import com.sm.repository.entity.AppointmentRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T14:05:50+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAppointmentRequestMapperImpl implements IAppointmentRequestMapper {

    @Override
    public AppointmentRequest toAppointmentRequest(CreateAppointmentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        AppointmentRequest.AppointmentRequestBuilder<?, ?> appointmentRequest = AppointmentRequest.builder();

        appointmentRequest.companyId( dto.getCompanyId() );
        appointmentRequest.message( dto.getMessage() );

        return appointmentRequest.build();
    }
}
