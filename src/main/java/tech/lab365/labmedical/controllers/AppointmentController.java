package tech.lab365.labmedical.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.lab365.labmedical.dtos.AppointmentRequestDTO;
import tech.lab365.labmedical.dtos.AppointmentResponseDTO;
import tech.lab365.labmedical.services.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> registerAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO appointmentResponseDTO = appointmentService.registerAppointment(appointmentRequestDTO);
        return ResponseEntity.ok(appointmentResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointment(@PathVariable Long id) {
        AppointmentResponseDTO appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,
                                                                    @Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        AppointmentResponseDTO updateAppointment = appointmentService.updateAppointment(id, appointmentRequestDTO);
        return ResponseEntity.ok(updateAppointment);
    }

}
