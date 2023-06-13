package com.sm.controller;

import com.sm.dto.request.UpdateAppointmentDto;
import com.sm.dto.response.AppointmentUpdateResponse;
import com.sm.repository.entity.Appointment;
import com.sm.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.sm.constants.ApiUrls.*;

@RestController
@RequestMapping(APPOINTMENT)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

@PutMapping(UPDATE)
    public ResponseEntity<AppointmentUpdateResponse> updateAppointment(@RequestBody UpdateAppointmentDto dto){
        return ResponseEntity.ok(appointmentService.updateAppointment(dto));
}
    @PutMapping(UPDATEACTIVE)
    public ResponseEntity<String> updateAppointmentStatus(@RequestBody Long id){
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<String> deleteAppointmentStatus(@RequestBody Long id){
        return ResponseEntity.ok(appointmentService.deleteAppointmentStatus(id));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<Appointment>> findAll(){
    return ResponseEntity.ok(appointmentService.findAll());
    }
}
