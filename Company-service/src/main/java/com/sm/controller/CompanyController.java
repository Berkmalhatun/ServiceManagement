package com.sm.controller;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.request.UpdateRequestDto;
import com.sm.dto.response.CreateResponseDto;
import com.sm.repository.entity.Company;
import com.sm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(CREATE)
    public ResponseEntity<CreateResponseDto> createCompany(@RequestBody CreateRequestDto dto){
        return ResponseEntity.ok(companyService.createCompany(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Company> updateCompany(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }
    @DeleteMapping(DELETEBYNAME)
    public ResponseEntity<Company> deleteCompany(@RequestParam String name){
        return ResponseEntity.ok(companyService.deleteCompany(name));
    }
    @PutMapping(ACTIVEBYNAME)
    public ResponseEntity<Company> activeCompany(@RequestParam String name){
        return ResponseEntity.ok(companyService.activeCompany(name));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<Company>> findAll() {
       return ResponseEntity.ok(companyService.findAll());
    }

}
