package com.sm.service;

import com.sm.dto.request.CreateAppointmentRequestDto;
import com.sm.dto.request.UpdateAppointmentRequestDto;
import com.sm.exception.AppointmentRequestServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.IAppointmentRequestMapper;
import com.sm.rabbitmq.model.AppointmentRequestModel;
import com.sm.rabbitmq.producer.AppointmentRequestProducer;
import com.sm.repository.IAppointmentRequestRepository;
import com.sm.repository.ICompanyRepository;
import com.sm.repository.entity.AppointmentRequest;
import com.sm.repository.entity.Company;
import com.sm.repository.enums.EStatus;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentRequestService extends ServiceManager<AppointmentRequest,Long> {
    private final IAppointmentRequestRepository appointmentRequestRepository;
    private final ICompanyRepository companyRepository;

    private final AppointmentRequestProducer appointmentRequestProducer;

    public AppointmentRequestService(IAppointmentRequestRepository appointmentRequestRepository, ICompanyRepository companyRepository, AppointmentRequestProducer appointmentRequestProducer) {
        super(appointmentRequestRepository);
        this.appointmentRequestRepository = appointmentRequestRepository;
        this.companyRepository = companyRepository;
        this.appointmentRequestProducer = appointmentRequestProducer;
    }

    /**
     * Randevu talebi buradan oluşturulur.
     * @param dto
     * @return
     */
    public String createAppointmentRequest(CreateAppointmentRequestDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getCompanyId());
        if (!company.isPresent()) {
        throw new AppointmentRequestServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        AppointmentRequest appointmentRequest = IAppointmentRequestMapper.INSTANCE.toAppointmentRequest(dto);
        save(appointmentRequest);
        appointmentRequestProducer.sendNewAppointmentRequest(AppointmentRequestModel.builder()
                .id(appointmentRequest.getId()).message(appointmentRequest.getMessage()).build());
        return "Appointment request successful.";
    }

    /**
     * Randevu talebınde talep açıklaması degıstırılmek ıstenırse bu method kullanılır.
     * @param dto
     * @return
     */
    public String updateAppointmentRequest(UpdateAppointmentRequestDto dto) {
        Optional<AppointmentRequest> appointmentRequest= appointmentRequestRepository.findById(dto.getId());
        if (!appointmentRequest.isPresent())
            throw new AppointmentRequestServiceException(ErrorType.APPOINTMENT_REQUEST_NOT_FOUND);
        appointmentRequest.get().setMessage(dto.getMessage());
        update(appointmentRequest.get());
        return "Appointment request updated.";
    }

    /**
     * Randevu talebi silinmesi için kullanılır.
     * @param id
     * @return
     */
    public String deleteAppointmentRequest(Long id) {
        Optional<AppointmentRequest> appointmentRequest= appointmentRequestRepository.findById(id);
        if (!appointmentRequest.isPresent())
            throw new AppointmentRequestServiceException(ErrorType.APPOINTMENT_REQUEST_NOT_FOUND);
        appointmentRequest.get().setStatus(EStatus.DELETED);
        update(appointmentRequest.get());
        return "Appointment request deleted.";
    }
    }

