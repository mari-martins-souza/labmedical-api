package tech.lab365.labmedical.security.service;

import java.util.UUID;

public interface PatientToAppointmentService {
    boolean isAppointmentOfPatient(UUID patientId, UUID appointmentId);
}

