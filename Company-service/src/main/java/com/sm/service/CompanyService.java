package com.sm.service;

import com.sm.dto.request.CreateRequestDto;
import com.sm.dto.request.UpdateRequestDto;
import com.sm.dto.response.CreateResponseDto;
import com.sm.exception.CompanyServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.ICompanyMapper;
import com.sm.repository.ICompanyRepository;
import com.sm.repository.entity.Company;
import com.sm.repository.enums.EStatus;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company,Long> {
    private final ICompanyRepository companyRepository;


    public CompanyService(ICompanyRepository companyRepository) {
       super(companyRepository);
        this.companyRepository = companyRepository;
    }

    public CreateResponseDto createCompany(CreateRequestDto dto) {
       Company company = ICompanyMapper.INSTANCE.toCompany(dto);
       Optional<Company> company2 = companyRepository.findOptionalByName(company.getName());
        if (company2.isPresent())
         throw new CompanyServiceException(ErrorType.COMPANY_NAME_DUPLICATE);
       try {
           save(company);
       }catch (Exception e) {
           throw  new CompanyServiceException(ErrorType.COMPANY_NOT_CREATED);
       }
            return ICompanyMapper.INSTANCE.toCreateResponseDto(company);
    }

    public Company updateCompany(UpdateRequestDto dto) {
        Optional<Company> company = companyRepository.findOptionalByName(dto.getName());
            if(company.isEmpty()){
                throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
            }
            company.get().setName(dto.getName());
            company.get().setCompany_address(dto.getCompany_address());
            company.get().setCompany_degree(dto.getCompany_degree());
            company.get().setCommercial_registration_number(dto.getCommercial_registration_number());
            company.get().setTax_office(dto.getTax_office());
            company.get().setTax_identification_number(dto.getTax_identification_number());
            company.get().setAuthorized_person(dto.getAuthorized_person());
            company.get().setPhone_number(dto.getPhone_number());
            update(company.get());
            return company.get();
    }

    public Company deleteCompany(String name) {
        Optional<Company> company = companyRepository.findOptionalByName(name);
        if(company.isEmpty()){
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        company.get().setEStatus(EStatus.DELETED);
        update(company.get());
        return company.get();
    }

    public Company activeCompany(String name) {
        Optional<Company> company = companyRepository.findOptionalByName(name);
        if(company.isEmpty()){
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        if (company.get().getEStatus().equals(EStatus.ACTIVE)){
            throw new CompanyServiceException(ErrorType.COMPANY_ALREADY_ACTIVE);
        }
        company.get().setEStatus(EStatus.ACTIVE);
        update(company.get());
        return company.get();
    }
}
