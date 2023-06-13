package com.sm.mapper;


import com.sm.dto.response.AppointmentUpdateResponse;
import com.sm.repository.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppointmentMapper {
    IAppointmentMapper INSTANCE= Mappers.getMapper(IAppointmentMapper.class);

    @Mapping(target = "process", expression = "java(\"Detailed information part has been successfully changed.\")")
    AppointmentUpdateResponse toAppointmentUpdateResponse(final Appointment appointment);

}
