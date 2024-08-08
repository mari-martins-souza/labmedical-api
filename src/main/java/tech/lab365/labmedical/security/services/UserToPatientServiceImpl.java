package tech.lab365.labmedical.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.repositories.PatientRepository;

import java.util.UUID;

@Service
public class UserToPatientServiceImpl implements UserToPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UUID findPatientIdByUserId(UUID userId) {

        Patient patient = patientRepository.findByUser_UserId(userId);
        return patient != null ? patient.getId() : null;
    }
}

