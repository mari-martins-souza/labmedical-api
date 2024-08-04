package tech.lab365.labmedical.security.service;

import java.util.UUID;

public interface UserToPatientService {
    UUID findPatientIdByUserId(UUID userId);
}

