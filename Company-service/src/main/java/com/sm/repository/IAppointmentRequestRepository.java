package com.sm.repository;

import com.sm.repository.entity.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long> {

}
