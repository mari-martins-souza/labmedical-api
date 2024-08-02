package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.AppointmentRequestDTO;
import tech.lab365.labmedical.dtos.AppointmentResponseDTO;
import tech.lab365.labmedical.entities.Appointment;
import tech.lab365.labmedical.entities.Patient;

@Component
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentRequestDTO dto, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setReason(dto.getReason());
        appointment.setConsultDate(dto.getConsultDate());
        appointment.setConsultTime(dto.getConsultTime());
        appointment.setProblemDescrip(dto.getProblemDescrip());
        appointment.setPrescMed(dto.getPrescMed());
        appointment.setDosagesPrec(dto.getDosagesPrec());
        appointment.setPatient(patient);
        return appointment;
    }

    public AppointmentResponseDTO toResponseDTO (Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        Patient patient = appointment.getPatient();
        dto.setAppointment_id(appointment.getAppointment_id());
        dto.setPatientName(patient.getName());
        dto.setId(patient.getId());
        dto.setReason(appointment.getReason());
        dto.setConsultDate(appointment.getConsultDate());
        dto.setConsultTime(appointment.getConsultTime());
        dto.setProblemDescrip(appointment.getProblemDescrip());
        dto.setPrescMed(appointment.getPrescMed());
        dto.setDosagesPrec(appointment.getDosagesPrec());
        return dto;
    }

    public void updateAppointmentFromDTO(Appointment appointment, AppointmentRequestDTO dto) {
        appointment.setReason(dto.getReason());
        appointment.setConsultDate(dto.getConsultDate());
        appointment.setConsultTime(dto.getConsultTime());
        appointment.setProblemDescrip(dto.getProblemDescrip());
        appointment.setPrescMed(dto.getPrescMed());
        appointment.setDosagesPrec(dto.getDosagesPrec());
    }

}
