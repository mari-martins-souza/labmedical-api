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

    public AppointmentResponseDTO registerAppointment(AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        Patient patient =
                patientRepository.findById(appointmentRequestDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        if (appointmentRequestDTO.getReason() == null || appointmentRequestDTO.getReason().isEmpty()) {
            throw new BadRequestException("reason is mandatory");
        }

        if (appointmentRequestDTO.getConsultDate() == null) {
            throw new BadRequestException("consultDate is mandatory");
        }

        if (appointmentRequestDTO.getConsultTime() == null) {
            throw new BadRequestException("consultTime is mandatory");
        }

        if (appointmentRequestDTO.getId() == null) {
            throw new BadRequestException("patient id is mandatory");
        }

        if (appointmentRequestDTO.getProblemDescrip() == null || appointmentRequestDTO.getProblemDescrip().isEmpty()) {
            throw new BadRequestException("problemDescrip is mandatory");
        }

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

        if (!appointment.getPatient().getId().equals(appointmentRequestDTO.getId())) {
            throw new BadRequestException("Patient ID in the request does not match the Patient ID in the existing appointment");
        }

        if (appointmentRequestDTO.getReason() == null || appointmentRequestDTO.getReason().isEmpty()) {
            throw new BadRequestException("reason is mandatory");
        }

        if (appointmentRequestDTO.getConsultDate() == null) {
            throw new BadRequestException("consultDate is mandatory");
        }

        if (appointmentRequestDTO.getConsultTime() == null) {
            throw new BadRequestException("consultTime is mandatory");
        }

        if (appointmentRequestDTO.getId() == null) {
            throw new BadRequestException("patient id is mandatory");
        }

        if (appointmentRequestDTO.getProblemDescrip() == null || appointmentRequestDTO.getProblemDescrip().isEmpty()) {
            throw new BadRequestException("problemDescrip is mandatory");
        }

        appointmentMapper.updateAppointmentFromDTO(appointment, appointmentRequestDTO);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDTO(savedAppointment);
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}
