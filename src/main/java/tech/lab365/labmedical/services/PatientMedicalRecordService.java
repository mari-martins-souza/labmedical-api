package tech.lab365.labmedical.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.PatientMedicalRecordResponseDTO;
import tech.lab365.labmedical.entities.Appointment;
import tech.lab365.labmedical.entities.Exam;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.mappers.PatientMedicalRecordMapper;
import tech.lab365.labmedical.repositories.AppointmentRepository;
import tech.lab365.labmedical.repositories.ExamRepository;
import tech.lab365.labmedical.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientMedicalRecordService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final ExamRepository examRepository;
    private final PatientMedicalRecordMapper patientMedicalRecordMapper;

    @Autowired
    public PatientMedicalRecordService(PatientMedicalRecordMapper patientMedicalRecordMapper, ExamRepository examRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.patientMedicalRecordMapper = patientMedicalRecordMapper;
        this.examRepository = examRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public PatientMedicalRecordResponseDTO getPatientMedicalRecord(Long patient_id) {
        Patient patient = patientRepository.findById(patient_id)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found"));

        List<Exam> exams = examRepository.findByPatient_Id(patient_id);
        List<Appointment> appointments = appointmentRepository.findByPatient_Id(patient_id);

        return patientMedicalRecordMapper.toDto(patient, exams, appointments);
    }
}


