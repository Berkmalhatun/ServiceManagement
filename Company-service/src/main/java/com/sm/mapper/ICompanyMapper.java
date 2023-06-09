package com.sm.mapper;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.response.CreateResponseDto;
import com.sm.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ICompanyMapper {

    ICompanyMapper INSTANCE=Mappers.getMapper(ICompanyMapper.class);
    Company toCompany(final CreateRequestDto dto);

    @Mapping(target = "message", expression = "java(company.getName() + \" company has been successfully added.\")")
    CreateResponseDto toCreateResponseDto(final Company company);
}
