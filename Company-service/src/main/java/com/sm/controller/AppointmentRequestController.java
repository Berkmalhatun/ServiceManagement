package com.sm.controller;

import static com.sm.constants.ApiUrls.*;

import com.sm.dto.request.CreateAppointmentRequestDto;
import com.sm.dto.request.UpdateAppointmentRequestDto;
import com.sm.repository.entity.AppointmentRequest;
import com.sm.repository.entity.Company;
import com.sm.service.AppointmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(APPOINTMENTREQUEST)
public class AppointmentRequestController {
    private final AppointmentRequestService appointmentRequestService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createAppointmentRequest(@RequestBody CreateAppointmentRequestDto dto){
        return ResponseEntity.ok(appointmentRequestService.createAppointmentRequest(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateAppointmentRequest(@RequestBody UpdateAppointmentRequestDto dto){
        return ResponseEntity.ok(appointmentRequestService.updateAppointmentRequest(dto));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<String> deleteAppointmentRequest(@RequestParam Long id){
        return ResponseEntity.ok(appointmentRequestService.deleteAppointmentRequest(id));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<AppointmentRequest>> findAll() {
        return ResponseEntity.ok(appointmentRequestService.findAll());
    }
}
