package com.sm.service;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.request.UpdateEmailRequestDto;
import com.sm.dto.request.UpdateRequestDto;
import com.sm.dto.response.CreateAndDeleteResponseDto;
import com.sm.exception.EmployeeServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.IEmployeeMapper;
import com.sm.repository.IEmployeeRepository;
import com.sm.repository.entity.Employee;
import com.sm.repository.enums.EStatus;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends ServiceManager<Employee,Long> {
    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }

    /**
     * Personel yaratılırken email ve tc kimlik kontrolu yapılıyor.
     *
     * @param dto
     * @return
     */
    public CreateAndDeleteResponseDto createEmployee(CreateRequestDto dto) {
        Employee employee = IEmployeeMapper.INSTANCE.toEmployee(dto);
        Optional<Employee> employee2 = employeeRepository.findOptionalByEmail(dto.getEmail());

        if (employee2.isPresent())
            throw new EmployeeServiceException(ErrorType.EMAIL_DUPLICATE);
        Optional<Employee> employee3 = employeeRepository.findOptionalByIdentificationNumber(dto.getIdentificationNumber());
        if (employee3.isPresent())
            throw new EmployeeServiceException(ErrorType.IDENTIFICATION_NUMBER_DUPLICATE);
        try {
            save(employee);
        } catch (Exception e) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_CREATED);
        }
        return IEmployeeMapper.INSTANCE.toCreateResponseDto(employee);
    }

    /**
     * Burada mail adresini guncellemıyoruz.Maıl adresınden kullanıcı arayarak kullanıcı buluyoruz.
     * @param dto
     * @return
     */
    public Employee updateEmployee(UpdateRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findOptionalByEmail(dto.getEmail());
        if (!employee.isPresent())
            throw new EmployeeServiceException(ErrorType.EMAIL_NOT_FOUND);
        employee.get().setName(dto.getName());
        employee.get().setSurname(dto.getSurname());
        employee.get().setRole(dto.getRole());
        employee.get().setStarting_date(dto.getStarting_date());
        employee.get().setEnd_date(dto.getEnd_date());
        employee.get().setBloodGroup(dto.getBloodGroup());
        employee.get().setPhone_number(dto.getPhone_number());
        employee.get().setBirth_day(dto.getBirth_day());
        try {
            update(employee.get());
        } catch (Exception e) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_UPDATED);
        }
        return employee.get();
    }

    /**
     * Burada maıl adresını guncellıyoruz.Maıl adresını guncellemek ıcın kımlık numarasına gore arama yapıyoruz.
     * Eger baska bır kullanıcı bu e-mail kullanıyorsa  hata fırlatır.
     * @param dto
     * @return
     */
    public Employee updateEmployeeEmail(UpdateEmailRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findOptionalByIdentificationNumber(dto.getIdentificationNumber());
        if (!employee.isPresent()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        if(employeeRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new EmployeeServiceException(ErrorType.EMAIL_DUPLICATE);
        employee.get().setEmail(dto.getEmail());
        try {
            update(employee.get());
        } catch (Exception e) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_UPDATED);
        }
        return employee.get();
    }

    /**
     * Kımlık numarası email araması ile değişiyor.Farklı bırınde bu kımlık numarası varsa  hata fırlatır.
     * @param dto
     * @return
     */
    public Employee updateEmployeeIdentificationNumber(UpdateEmailRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findOptionalByEmail(dto.getEmail());
        if (!employee.isPresent()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        if (employeeRepository.findOptionalByIdentificationNumber(dto.getIdentificationNumber()).isPresent())
            throw new EmployeeServiceException(ErrorType.IDENTIFICATION_NUMBER_DUPLICATE);
        employee.get().setIdentificationNumber(dto.getIdentificationNumber());
        try {
            update(employee.get());
        } catch (Exception e) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_UPDATED);
        }
        return employee.get();
    }

    /**
     * Kulanıcının durumunu delete yapmak ıcın kullanılır.
     * @param email
     * @return
     */
    public CreateAndDeleteResponseDto deleteEmployee(String email) {
        Optional<Employee> employee= employeeRepository.findOptionalByEmail(email);
        if (employee.isPresent())
            employee.get().setStatus(EStatus.DELETED);
        return IEmployeeMapper.INSTANCE.toDeleteResponseDto(employee.get());
    }

    /**
     * Kullanıcının durumunu aktife cekmek ıcın kullanılır.
     * @param email
     * @return
     */
    public CreateAndDeleteResponseDto activeEmployee(String email) {
        Optional<Employee> employee= employeeRepository.findOptionalByEmail(email);
        if (employee.isPresent())
            employee.get().setStatus(EStatus.ACTIVE);
        return IEmployeeMapper.INSTANCE.toActiveResponseDto(employee.get());
    }

}
