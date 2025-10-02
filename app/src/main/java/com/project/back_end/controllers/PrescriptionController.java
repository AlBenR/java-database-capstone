package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.project.back_end.models.Prescription;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.Service;
import java.util.Map;

@RestController
@RequestMapping("${api.path}prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final Service service;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, Service service) {
        this.prescriptionService = prescriptionService;
        this.service = service;
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> savePrescription(@RequestBody Prescription prescription,
                                                               @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "doctor");
        if (validationResponse.getStatusCode().isError()) {
            return validationResponse;
        }
        return prescriptionService.savePrescription(prescription);
    }

    @GetMapping("/{appointmentId}/{token}")
    public ResponseEntity<Map<String, Object>> getPrescription(@PathVariable Long appointmentId,
                                                              @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "doctor");
        if (validationResponse.getStatusCode().isError()) {
            return ResponseEntity.status(validationResponse.getStatusCode()).body(Map.of("error", "Invalid token"));
        }
        return prescriptionService.getPrescription(appointmentId);
    }
}