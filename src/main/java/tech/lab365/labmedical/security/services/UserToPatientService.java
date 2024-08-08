package tech.lab365.labmedical.security.services;

import java.util.UUID;

public interface UserToPatientService {
    UUID findPatientIdByUserId(UUID userId);
}

