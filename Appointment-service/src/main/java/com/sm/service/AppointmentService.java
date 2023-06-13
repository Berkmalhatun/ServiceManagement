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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String updateActiveAppointmentStatus(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        appointment.get().setStatus(EStatus.ACTIVE);
        update(appointment.get());
        return "Successfully activated.";
    }

    /**
     * Burada randevu taleplerını pasif yaparız.
     * @param id
     * @return
     */
    public String updatePassiveAppointmentStatus(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        appointment.get().setStatus(EStatus.PASSIVE);
        update(appointment.get());
        return "Successfully deactivated.";
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
        //
    }

    /**
     * Deleted listesi buradan cekılır
     * @return
     */
    public List<Appointment> findDeletedList(){
        List<Appointment> appointmentsList = appointmentRepository.findAll();
        if (appointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        List<Appointment> deleteAppointmentsList = appointmentsList.stream().filter(x->x.getStatus() == EStatus.DELETED)
                .collect(Collectors.toList());
        if (deleteAppointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_DELETED_NOT_FOUND);
        }
        return deleteAppointmentsList;
    }

    /**
     * Active listesi buradan cekılır
     * @return
     */
    public List<Appointment> findActiveList(){
        List<Appointment> appointmentsList = appointmentRepository.findAll();
        if (appointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        List<Appointment> activeAppointmentsList = appointmentsList.stream().filter(x->x.getStatus() == EStatus.ACTIVE)
                .collect(Collectors.toList());
        if (activeAppointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_ACTIVE_NOT_FOUND);
        }
        return activeAppointmentsList;
    }

    /**
     * Passive Listesi buradan cekılır
     * @return
     */
    public List<Appointment> findPassiveList(){
        List<Appointment> appointmentsList = appointmentRepository.findAll();
        if (appointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_NOT_FOUND);
        }
        List<Appointment> passiveAppointmentsList = appointmentsList.stream().filter(x->x.getStatus() == EStatus.PASSIVE)
                .collect(Collectors.toList());
        if (passiveAppointmentsList.isEmpty()) {
            throw new AppointmentServiceException(ErrorType.APPOINTMENT_PASSIVE_NOT_FOUND);
        }
        return passiveAppointmentsList;
    }


}
