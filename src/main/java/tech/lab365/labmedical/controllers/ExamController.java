package tech.lab365.labmedical.controllers;

import jakarta.servlet.http.HttpServletRequest;
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


import jakarta.validation.Valid;
import tech.lab365.labmedical.dtos.AppointmentResponseDTO;
import tech.lab365.labmedical.dtos.ExamRequestDTO;
import tech.lab365.labmedical.dtos.ExamResponseDTO;
import tech.lab365.labmedical.security.services.PatientToExamService;
import tech.lab365.labmedical.security.services.UserToPatientService;
import tech.lab365.labmedical.services.ExamService;

import java.util.UUID;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private UserToPatientService userToPatientService;

    @Autowired
    private PatientToExamService patientToExamService;

    @PostMapping
    public ResponseEntity<ExamResponseDTO> registerExam(@Valid @RequestBody ExamRequestDTO examRequestDTO) throws BadRequestException {
        ExamResponseDTO examResponseDTO = examService.registerExam(examRequestDTO);
        return new ResponseEntity<>(examResponseDTO, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ExamResponseDTO> getExam(@PathVariable UUID id) {
//        ExamResponseDTO exam = examService.getExam(id);
//        return ResponseEntity.ok(exam);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> updateExam(@PathVariable UUID id,
                                                      @Valid @RequestBody ExamRequestDTO examRequestDTO) throws BadRequestException {
        ExamResponseDTO updateExam = examService.updateExam(id, examRequestDTO);
        return ResponseEntity.ok(updateExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable UUID id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> getExam(@PathVariable("id") UUID pathId,
                                                                 HttpServletRequest request) {
        logger.info("Entering getExam with pathId: {}", pathId);

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
            logger.info("User has ROLE_ADMIN or ROLE_MEDICO, accessing exam directly.");

            ExamResponseDTO examResponseDTO = examService.getExam(pathId);
            return ResponseEntity.ok(examResponseDTO);
        }

        UUID patientId = userToPatientService.findPatientIdByUserId(userId);
        logger.info("Patient ID for User ID {}: {}", userId, patientId);

        if (patientId == null) {
            logger.error("Patient ID is null for User ID {}", userId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        boolean isExamOfPatient = patientToExamService.isExamOfPatient(patientId, pathId);
        logger.info("Exam ID {} is of patient {}: {}", pathId, patientId, isExamOfPatient);

        if (isExamOfPatient) {
            logger.info("Exam ID matches pathId, returning exam.");
            ExamResponseDTO examResponseDTO = examService.getExam(pathId);
            return ResponseEntity.ok(examResponseDTO);
        } else {
            logger.error("Exam ID mismatch or not found for pathId: {}", pathId);
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
