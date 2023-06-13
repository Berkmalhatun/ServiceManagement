package com.sm.service;

import com.sm.dto.request.UpdateAppointmentDto;
import com.sm.dto.response.AppointmentUpdateResponse;
import com.sm.exception.AppointmentServiceException;
import com.sm.exception.ErrorType;
import com.sm.mapper.IAppointmentMapper;
import com.sm.rabbitmq.model.AppointmentRequestModel;
import com.sm.repository.IAppointmentRepository;
import com.sm.repository.entity.Appointment;
import com.sm.repository.enums.EStatus;
import com.sm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService extends ServiceManager<Appointment,Long> {
    private final IAppointmentRepository  appointmentRepository;

    public AppointmentService(IAppointmentRepository appointmentRepository) {
        super(appointmentRepository);
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Buraya rabbit ile gelen istek pasif durumda gozukur. Fakat bu ıstegı gonderen fırma bunu aktıf olarak gorur.
     * @param model
     * @return
     */
    public Boolean createAppointmentWithRabbitMq(AppointmentRequestModel model) {
        Appointment appointment = Appointment.builder().appointmentRequestId(model.getId()).message(model.getMessage()).build();
        save(appointment);
        return true;
    }

    /**
     * Burada acıklamayı guncellerız.
     * @param dto
     * @return
     */
    public AppointmentUpdateResponse updateAppointment(UpdateAppointmentDto dto) {
        Optional<Appointment> appointment = appointmentRepository.findById(dto.getId());
        if (!appointment.isPresent()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        appointment.get().setMessage(dto.getMessage());
        update(appointment.get());
        return IAppointmentMapper.INSTANCE.toAppointmentUpdateResponse(appointment.get());
    }

    /**
     * Statusu buradan aktıfe cekıyoruz.
     * @param id
     * @return
     */
    public String updateAppointmentStatus(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        appointment.get().setStatus(EStatus.ACTIVE);
        update(appointment.get());
        return "Successfully activated";
    }

    /**
     * Burada randevu istegi silinir.
     * @param id
     * @return
     */
    public String deleteAppointmentStatus(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        appointment.get().setStatus(EStatus.DELETED);
        update(appointment.get());
        return "Successfully deleted.";
    }
}
