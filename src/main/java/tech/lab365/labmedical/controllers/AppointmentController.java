package tech.lab365.labmedical.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import tech.lab365.labmedical.dtos.AppointmentRequestDTO;
import tech.lab365.labmedical.dtos.AppointmentResponseDTO;
import tech.lab365.labmedical.security.service.PatientToAppointmentService;
import tech.lab365.labmedical.services.AppointmentService;
import tech.lab365.labmedical.security.service.UserToPatientService;

import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserToPatientService userToPatientService;

    @Autowired
    private PatientToAppointmentService patientToAppointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> registerAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.registerAppointment(appointmentRequestDTO);
        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable UUID id,
                                                                    @Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        AppointmentResponseDTO updateAppointment = appointmentService.updateAppointment(id, appointmentRequestDTO);
        return ResponseEntity.ok(updateAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointment(@PathVariable("id") UUID pathId,
                                                                 HttpServletRequest request) {
        logger.info("Entering getAppointment with pathId: {}", pathId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = getUserIdFromAuthentication(auth);

        if (userId == null) {
            logger.error("User ID is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        logger.info("User ID from token: {}", userId);

        if (auth.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("ROLE_ADMIN") ||
                        grantedAuthority.getAuthority().equals("ROLE_MEDICO"))) {
            logger.info("User has ROLE_ADMIN or ROLE_MEDICO, accessing appointment directly.");
            AppointmentResponseDTO appointmentResponseDTO = appointmentService.getAppointment(pathId);
            return ResponseEntity.ok(appointmentResponseDTO);
        }

        UUID patientId = userToPatientService.findPatientIdByUserId(userId);
        logger.info("Patient ID for User ID {}: {}", userId, patientId);

        if (patientId == null) {
            logger.error("Patient ID is null for User ID {}", userId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        boolean isAppointmentOfPatient = patientToAppointmentService.isAppointmentOfPatient(patientId, pathId);
        logger.info("Appointment ID {} is of patient {}: {}", pathId, patientId, isAppointmentOfPatient);

        if (isAppointmentOfPatient) {
            logger.info("Appointment ID matches pathId, returning appointment.");
            AppointmentResponseDTO appointmentResponseDTO = appointmentService.getAppointment(pathId);
            return ResponseEntity.ok(appointmentResponseDTO);
        } else {
            logger.error("Appointment ID mismatch or not found for pathId: {}", pathId);
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

