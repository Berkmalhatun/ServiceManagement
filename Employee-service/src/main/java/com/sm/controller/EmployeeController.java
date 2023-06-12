package com.sm.controller;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.request.UpdateEmailRequestDto;
import com.sm.dto.request.UpdateRequestDto;
import com.sm.dto.response.CreateAndDeleteResponseDto;
import com.sm.repository.entity.Employee;
import com.sm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(CREATE)
    public ResponseEntity<CreateAndDeleteResponseDto> createEmployee(@RequestBody CreateRequestDto dto){
        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(employeeService.updateEmployee(dto));
    }
    @PutMapping(UPDATEEMAIL)
    public ResponseEntity<Employee> updateEmployeeEmail(@RequestBody UpdateEmailRequestDto dto){
        return ResponseEntity.ok(employeeService.updateEmployeeEmail(dto));
    }
    @PutMapping(UPDATEIDENTIFICATIONNUMBER)
    public ResponseEntity<Employee> updateEmployeeIdentificationNumber(@RequestBody UpdateEmailRequestDto dto){
        return ResponseEntity.ok(employeeService.updateEmployeeIdentificationNumber(dto));
    }

    @DeleteMapping(DELETEBYNAME)
    public ResponseEntity<CreateAndDeleteResponseDto> deleteEmployee(@RequestParam String email){
        return ResponseEntity.ok(employeeService.deleteEmployee(email));
    }
    @PutMapping(ACTIVEBYNAME)
    public ResponseEntity<CreateAndDeleteResponseDto> activeEmployee(@RequestParam String email){
        return ResponseEntity.ok(employeeService.activeEmployee(email));
  }
    @GetMapping(FINDALL)
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }
}
