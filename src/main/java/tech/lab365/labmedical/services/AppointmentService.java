package tech.lab365.labmedical.services;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.AppointmentRequestDTO;
import tech.lab365.labmedical.dtos.AppointmentResponseDTO;
import tech.lab365.labmedical.entities.Appointment;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.mappers.AppointmentMapper;
import tech.lab365.labmedical.repositories.AppointmentRepository;
import tech.lab365.labmedical.repositories.PatientRepository;

@Service
public class AppointmentService {

private final PatientRepository patientRepository;
private final AppointmentRepository appointmentRepository;
private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(PatientRepository patientRepository, AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public AppointmentResponseDTO registerAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Patient patient = patientRepository.findById(appointmentRequestDTO.getPatient_id()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Appointment appointment = appointmentMapper.toAppointment(appointmentRequestDTO, patient);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(savedAppointment);
    }

    public AppointmentResponseDTO getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Appointment not found"));
        return appointmentMapper.toResponseDTO(appointment);
    }

    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Appointment not found"));

        if (!appointment.getPatient().getPatientId().equals(appointmentRequestDTO.getPatient_id())) {
            throw new BadRequestException("Patient ID in the request does not match the Patient ID in the existing appointment");
        }

        appointmentMapper.updateAppointmentFromDTO(appointment, appointmentRequestDTO);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(savedAppointment);
    }


}
