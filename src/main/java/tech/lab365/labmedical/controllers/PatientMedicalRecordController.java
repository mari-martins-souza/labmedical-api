package tech.lab365.labmedical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lab365.labmedical.dtos.PatientMedicalRecordResponseDTO;
import tech.lab365.labmedical.services.PatientMedicalRecordService;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientMedicalRecordController {

    @Autowired
    private PatientMedicalRecordService patientMedicalRecordService;

    @GetMapping("/{id}/medical-record")
    public ResponseEntity<PatientMedicalRecordResponseDTO> getPatientMedicalRecord(@PathVariable UUID id) {
        PatientMedicalRecordResponseDTO medicalRecord = patientMedicalRecordService.getPatientMedicalRecord(id);
        return ResponseEntity.ok(medicalRecord);
    }

}
