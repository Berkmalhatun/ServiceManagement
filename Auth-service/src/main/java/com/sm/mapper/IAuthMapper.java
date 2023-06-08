package com.sm.mapper;

import com.sm.dto.request.RegisterRequestDto;
import com.sm.dto.response.RegisterResponseDto;
import com.sm.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);
    @Mapping(target = "message", constant = "Registration Successfully completed. You can login.")
    RegisterResponseDto toRegisterResponseDto(final  Auth auth);
}
