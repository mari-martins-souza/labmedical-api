package tech.lab365.labmedical.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getMessage().contains("cpf")) {
            return new ResponseEntity<>("cpf already exists in database", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        if (e.getMessage().equals("Patient not found")) {
//            return new ResponseEntity<>("The patient with the given ID was not found in database.", HttpStatus.NOT_FOUND);
//        } else if (e.getMessage().equals("Authentication failed or unauthorized")) {
//            return new ResponseEntity<>("Failed to authenticate or unauthorized service for access.", HttpStatus.UNAUTHORIZED);
//        } else if (e.getMessage().equals("Bad Request")) {
//            return new ResponseEntity<>("Request is poorly formed or missing a mandatory field.", HttpStatus.BAD_REQUEST);
//        } else {
//            return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        if (e.getMessage().equals("Patient not found")) {
            return new ResponseEntity<>("The patient with the given ID was not found in database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({AuthenticationException.class})
//    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
//        return new ResponseEntity<>("Failed to authenticate or unauthorized service for access.", HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
