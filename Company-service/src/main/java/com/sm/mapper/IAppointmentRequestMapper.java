package com.sm.mapper;

import com.sm.dto.request.CreateAppointmentRequestDto;
import com.sm.repository.entity.AppointmentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppointmentRequestMapper {
    IAppointmentRequestMapper INSTANCE= Mappers.getMapper(IAppointmentRequestMapper.class);


    AppointmentRequest toAppointmentRequest(final CreateAppointmentRequestDto dto);
}
