package com.sm.mapper;

import com.sm.dto.response.AppointmentUpdateResponse;
import com.sm.repository.entity.Appointment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T20:15:59+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAppointmentMapperImpl implements IAppointmentMapper {

    @Override
    public AppointmentUpdateResponse toAppointmentUpdateResponse(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentUpdateResponse.AppointmentUpdateResponseBuilder appointmentUpdateResponse = AppointmentUpdateResponse.builder();

        appointmentUpdateResponse.message( appointment.getMessage() );

        appointmentUpdateResponse.process( "Detailed information part has been successfully changed." );

        return appointmentUpdateResponse.build();
    }
}
