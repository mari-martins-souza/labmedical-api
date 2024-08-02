package tech.lab365.labmedical.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.lab365.labmedical.dtos.MedicalRecordListResponseDTO;
import tech.lab365.labmedical.dtos.PatientGetAllResponseDTO;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.PatientResponseDTO;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) throws BadRequestException {
        Patient patientResponseDTO = patientService.registerPatient(patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PatientGetAllResponseDTO>> getAllPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email) {
        List<PatientGetAllResponseDTO> patients = patientService.getAllPatients(name, phone, email);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatient(@PathVariable Long id) {
        PatientResponseDTO patient = patientService.getPatient(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        try {
            PatientResponseDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);
            return ResponseEntity.ok(updatedPatient.toString());
        } catch (BadRequestException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medical-record-list")
    public ResponseEntity<Page<MedicalRecordListResponseDTO>> getAllMedicalRecords(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            Pageable pageable
    ) {
        Page<MedicalRecordListResponseDTO> medicalRecords = patientService.getAllMedicalRecords(name, id, pageable);
        return ResponseEntity.ok(medicalRecords);
    }
}