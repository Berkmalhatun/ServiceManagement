package com.sm.mapper;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.response.CreateAndDeleteResponseDto;
import com.sm.repository.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeMapper {
    IEmployeeMapper INSTANCE= Mappers.getMapper(IEmployeeMapper.class);

    Employee toEmployee(final CreateRequestDto dto);

    @Mapping(target = "message", expression = "java(employee.getName() + \" \" + employee.getSurname() + \" employee has been successfully added.\")")
    CreateAndDeleteResponseDto toCreateResponseDto(final Employee employee);

    @Mapping(target = "message", expression = "java(employee.getName() + \" \" + employee.getSurname() + \" employee has been successfully deleted.\")")
    CreateAndDeleteResponseDto toDeleteResponseDto(final Employee employee);

    @Mapping(target = "message", expression = "java(employee.getName() + \" \" + employee.getSurname() + \" employee has been successfully actived.\")")
    CreateAndDeleteResponseDto toActiveResponseDto(final Employee employee);
}
