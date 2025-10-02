package com.project.back_end.services;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.back_end.model.Prescription;
import com.project.back_end.repo.PrescriptionRepository;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public ResponseEntity<Map<String, String>> savePrescription(Prescription prescription) {
        Map<String, String> response = new HashMap<>();
        try {
            List<Prescription> existingPrescriptions = prescriptionRepository.findByAppointmentId(prescription.getAppointmentId());
            if (!existingPrescriptions.isEmpty()) {
                response.put("error", "Prescription already exists for this appointment");
                return ResponseEntity.badRequest().body(response);
            }
            
            prescriptionRepository.save(prescription);
            response.put("message", "Prescription saved successfully");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("error", "Failed to save prescription");
            return ResponseEntity.status(500).body(response);
        }
    }

    public ResponseEntity<Map<String, Object>> getPrescription(Long appointmentId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Prescription> prescriptions = prescriptionRepository.findByAppointmentId(appointmentId);
            if (prescriptions.isEmpty()) {
                response.put("message", "No prescription found for this appointment");
                return ResponseEntity.ok(response);
            }
            
            response.put("prescription", prescriptions.get(0));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Failed to get prescription");
            return ResponseEntity.status(500).body(response);
        }
    }
}