package tech.lab365.labmedical.error;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getMessage().contains("cpf")) {
            return new ResponseEntity<>("cpf already exists in database", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        if (e.getMessage().equals("Patient not found")) {
            return new ResponseEntity<>("The patient with the given ID was not found in database.", HttpStatus.NOT_FOUND);
        }
        if (e.getMessage().equals("Appointment not found")) {
            return new ResponseEntity<>("The appointment with the given ID was not found in database.", HttpStatus.NOT_FOUND);
        }
        if (e.getMessage().equals("Exam not found")) {
            return new ResponseEntity<>("The exam with the given ID was not found in database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
