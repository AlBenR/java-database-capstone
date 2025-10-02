package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.project.back_end.models.Appointment;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.Service;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final Service service;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, Service service) {
        this.appointmentService = appointmentService;
        this.service = service;
    }

    @GetMapping("/{date}/{patientName}/{token}")
    public ResponseEntity<Map<String, Object>> getAppointments(@PathVariable String date, 
                                                              @PathVariable String patientName, 
                                                              @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "doctor");
        if (validationResponse.getStatusCode().isError()) {
            return ResponseEntity.status(validationResponse.getStatusCode()).body(Map.of("error", "Invalid token"));
        }
        Map<String, Object> result = appointmentService.getAppointment(patientName, java.time.LocalDate.parse(date), token);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> bookAppointment(@RequestBody Appointment appointment, 
                                                              @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "patient");
        if (validationResponse.getStatusCode().isError()) {
            return validationResponse;
        }
        
        int validationResult = service.validateAppointment(appointment);
        if (validationResult != 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid appointment"));
        }
        
        int result = appointmentService.bookAppointment(appointment);
        if (result == 1) {
            return ResponseEntity.status(201).body(Map.of("message", "Appointment booked successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to book appointment"));
        }
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, String>> updateAppointment(@RequestBody Appointment appointment, 
                                                                @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "patient");
        if (validationResponse.getStatusCode().isError()) {
            return validationResponse;
        }
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> cancelAppointment(@PathVariable long id, 
                                                                @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "patient");
        if (validationResponse.getStatusCode().isError()) {
            return validationResponse;
        }
        return appointmentService.cancelAppointment(id, token);
    }
}