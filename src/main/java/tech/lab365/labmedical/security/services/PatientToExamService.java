package tech.lab365.labmedical.security.services;

import java.util.UUID;

public interface PatientToExamService {
    boolean isExamOfPatient(UUID patientId, UUID examId);
}
