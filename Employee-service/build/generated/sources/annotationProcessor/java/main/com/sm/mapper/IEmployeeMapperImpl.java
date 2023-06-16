package com.sm.mapper;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.response.CreateAndDeleteResponseDto;
import com.sm.repository.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T09:08:51+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IEmployeeMapperImpl implements IEmployeeMapper {

    @Override
    public Employee toEmployee(CreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee.EmployeeBuilder<?, ?> employee = Employee.builder();

        employee.name( dto.getName() );
        employee.surname( dto.getSurname() );
        employee.identificationNumber( dto.getIdentificationNumber() );
        employee.role( dto.getRole() );
        employee.starting_date( dto.getStarting_date() );
        employee.end_date( dto.getEnd_date() );
        employee.email( dto.getEmail() );
        employee.phone_number( dto.getPhone_number() );
        employee.birth_day( dto.getBirth_day() );

        return employee.build();
    }

    @Override
    public CreateAndDeleteResponseDto toCreateResponseDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        CreateAndDeleteResponseDto.CreateAndDeleteResponseDtoBuilder createAndDeleteResponseDto = CreateAndDeleteResponseDto.builder();

        createAndDeleteResponseDto.name( employee.getName() );
        createAndDeleteResponseDto.surname( employee.getSurname() );

        createAndDeleteResponseDto.message( employee.getName() + " " + employee.getSurname() + " employee has been successfully added." );

        return createAndDeleteResponseDto.build();
    }

    @Override
    public CreateAndDeleteResponseDto toDeleteResponseDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        CreateAndDeleteResponseDto.CreateAndDeleteResponseDtoBuilder createAndDeleteResponseDto = CreateAndDeleteResponseDto.builder();

        createAndDeleteResponseDto.name( employee.getName() );
        createAndDeleteResponseDto.surname( employee.getSurname() );

        createAndDeleteResponseDto.message( employee.getName() + " " + employee.getSurname() + " employee has been successfully deleted." );

        return createAndDeleteResponseDto.build();
    }

    @Override
    public CreateAndDeleteResponseDto toActiveResponseDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        CreateAndDeleteResponseDto.CreateAndDeleteResponseDtoBuilder createAndDeleteResponseDto = CreateAndDeleteResponseDto.builder();

        createAndDeleteResponseDto.name( employee.getName() );
        createAndDeleteResponseDto.surname( employee.getSurname() );

        createAndDeleteResponseDto.message( employee.getName() + " " + employee.getSurname() + " employee has been successfully actived." );

        return createAndDeleteResponseDto.build();
    }
}
