package tech.lab365.labmedical.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lab365.labmedical.dtos.PatientMedicalRecordResponseDTO;
import tech.lab365.labmedical.security.services.UserToPatientService;
import tech.lab365.labmedical.services.PatientMedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientMedicalRecordController {

    private static final Logger logger = LoggerFactory.getLogger(PatientMedicalRecordController.class);

    @Autowired
    private PatientMedicalRecordService patientMedicalRecordService;

    @Autowired
    private UserToPatientService userToPatientService;

    @GetMapping("/{id}/medical-record")
    public ResponseEntity<PatientMedicalRecordResponseDTO> getPatientMedicalRecord(
            @PathVariable("id") UUID pathId, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = getUserIdFromAuthentication(auth);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (auth.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN") ||
                        grantedAuthority.getAuthority().equals("ROLE_MEDICO"))) {
            PatientMedicalRecordResponseDTO medicalRecord = patientMedicalRecordService.getPatientMedicalRecord(pathId);
            return ResponseEntity.ok(medicalRecord);
        }

        UUID patientId = userToPatientService.findPatientIdByUserId(userId);
        if (patientId != null && patientId.equals(pathId)) {
            PatientMedicalRecordResponseDTO medicalRecord = patientMedicalRecordService.getPatientMedicalRecord(pathId);
            return ResponseEntity.ok(medicalRecord);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private UUID getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            logger.error("Authentication or principal is null");
            return null;
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userIdString = jwt.getClaim("sub");
        logger.info("Extracted user ID string from JWT: {}", userIdString);

        if (userIdString == null || userIdString.isEmpty()) {
            logger.error("User ID is null or empty");
            return null;
        }

        try {
            return UUID.fromString(userIdString);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID string: {}", userIdString, e);
            return null;
        }
    }
}