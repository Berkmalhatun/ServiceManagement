package com.sm.repository;

import com.sm.repository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findOptionalByEmail(String email);
    Optional<Employee> findOptionalByName(String name);
   Optional<Employee> findOptionalByIdentificationNumber(String identificationNumber);
}
