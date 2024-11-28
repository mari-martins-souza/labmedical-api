package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.PatientMedicalRecordResponseDTO;
import tech.lab365.labmedical.entities.Appointment;
import tech.lab365.labmedical.entities.Exam;
import tech.lab365.labmedical.entities.Patient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMedicalRecordMapper {

    private final ExamMapper examMapper;
    private final AppointmentMapper appointmentMapper;

    public PatientMedicalRecordMapper(ExamMapper examMapper, AppointmentMapper appointmentMapper) {
        this.examMapper = examMapper;
        this.appointmentMapper = appointmentMapper;
    }

    public PatientMedicalRecordResponseDTO toDto(Patient patient, List<Exam> exams, List<Appointment> appointments) {
        PatientMedicalRecordResponseDTO dto = new PatientMedicalRecordResponseDTO();
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setBirthdate(patient.getBirthdate());
        dto.setCpf(patient.getCpf());
        dto.setId(patient.getId());
        dto.setGender(patient.getGender());
        dto.setRg(patient.getRg());
        dto.setIssOrg(patient.getIssOrg());
        dto.setMaritalStatus(patient.getMaritalStatus());
        dto.setPhone(patient.getPhone());
        dto.setPlaceOfBirth(patient.getPlaceOfBirth());
        dto.setHealthInsuranceVal(patient.getHealthInsuranceVal());
        dto.setZipcode(patient.getZipcode());
        dto.setStreet(patient.getStreet());
        dto.setAddressNumber(patient.getAddressNumber());
        dto.setComplement(patient.getComplement());
        dto.setReferencePoint(patient.getReferencePoint());
        dto.setNeighborhood(patient.getNeighborhood());
        dto.setCity(patient.getCity());
        dto.setState(patient.getState());
        dto.setHealthInsurance(patient.getHealthInsurance());
        dto.setEmergCont(patient.getEmergCont());
        dto.setEmergContNumber(patient.getEmergContNumber());
        dto.setListOfAllergies(patient.getListOfAllergies());
        dto.setCareList(patient.getCareList());
        if (exams != null) {
            dto.setExams(exams.stream().map(examMapper::toResponseDTO).collect(Collectors.toList()));
        }
        if (appointments != null) {
            dto.setAppointments(appointments.stream().map(appointmentMapper::toResponseDTO).collect(Collectors.toList()));
        }
        return dto;
    }
}
