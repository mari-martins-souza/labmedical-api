package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.MedicalRecordListResponseDTO;
import tech.lab365.labmedical.dtos.PatientGetAllResponseDTO;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.PatientResponseDTO;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.entities.User;

@Component
public class PatientMapper {
    public Patient toPatient(PatientRequestDTO dto, User user) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setBirthdate(dto.getBirthdate());
        patient.setCpf(dto.getCpf());
        patient.setRg(dto.getRg());
        patient.setIssOrg(dto.getIssOrg());
        patient.setMaritalStatus(dto.getMaritalStatus());
        patient.setPhone(dto.getPhone());
        patient.setEmail(dto.getEmail());
        patient.setPlaceOfBirth(dto.getPlaceOfBirth());
        patient.setEmergCont(dto.getEmergCont());
        patient.setEmergContNumber(dto.getEmergContNumber());
        patient.setListOfAllergies(dto.getListOfAllergies());
        patient.setCareList(dto.getCareList());
        patient.setHealthInsurance(dto.getHealthInsurance());
        patient.setHealthInsuranceNumber(dto.getHealthInsuranceNumber());
        patient.setHealthInsuranceVal(dto.getHealthInsuranceVal());
        patient.setZipcode(dto.getZipcode());
        patient.setStreet(dto.getStreet());
        patient.setAddressNumber(dto.getAddressNumber());
        patient.setComplement(dto.getComplement());
        patient.setReferencePoint(dto.getReferencePoint());
        patient.setNeighborhood(dto.getNeighborhood());
        patient.setCity(dto.getCity());
        patient.setState(dto.getState());
        patient.setUser(user);
        return patient;
    }

    public PatientResponseDTO toResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setBirthdate(patient.getBirthdate());
        dto.setCpf(patient.getCpf());
        dto.setRg(patient.getRg());
        dto.setIssOrg(patient.getIssOrg());
        dto.setMaritalStatus(patient.getMaritalStatus());
        dto.setPhone(patient.getPhone());
        dto.setEmail(patient.getEmail());
        dto.setPlaceOfBirth(patient.getPlaceOfBirth());
        dto.setEmergCont(patient.getEmergCont());
        dto.setEmergContNumber(patient.getEmergContNumber());
        dto.setListOfAllergies(patient.getListOfAllergies());
        dto.setCareList(patient.getCareList());
        dto.setHealthInsurance(patient.getHealthInsurance());
        dto.setHealthInsuranceNumber(patient.getHealthInsuranceNumber());
        dto.setHealthInsuranceVal(patient.getHealthInsuranceVal());
        dto.setZipcode(patient.getZipcode());
        dto.setStreet(patient.getStreet());
        dto.setAddressNumber(patient.getAddressNumber());
        dto.setComplement(patient.getComplement());
        dto.setReferencePoint(patient.getReferencePoint());
        dto.setNeighborhood(patient.getNeighborhood());
        dto.setCity(patient.getCity());
        dto.setState(patient.getState());
        dto.setPatient_id((patient.getPatientId()));
        return dto;
    }

    public PatientGetAllResponseDTO toResponseAllDTO(Patient patient) {
        PatientGetAllResponseDTO dto = new PatientGetAllResponseDTO();
        dto.setName(patient.getName());
        dto.setBirthdate(patient.getBirthdate());
        dto.setPhone(patient.getPhone());
        dto.setEmail(patient.getEmail());
        dto.setHealthInsurance(patient.getHealthInsurance());
        return dto;
    }

    public void updatePatientFromDto(PatientRequestDTO dto, Patient patient, User user) {
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setBirthdate(dto.getBirthdate());
        patient.setCpf(dto.getCpf());
        patient.setRg(dto.getRg());
        patient.setIssOrg(dto.getIssOrg());
        patient.setMaritalStatus(dto.getMaritalStatus());
        patient.setPhone(dto.getPhone());
        patient.setEmail(dto.getEmail());
        patient.setPlaceOfBirth(dto.getPlaceOfBirth());
        patient.setEmergCont(dto.getEmergCont());
        patient.setEmergContNumber(dto.getEmergContNumber());
        patient.setListOfAllergies(dto.getListOfAllergies());
        patient.setCareList(dto.getCareList());
        patient.setHealthInsurance(dto.getHealthInsurance());
        patient.setHealthInsuranceNumber(dto.getHealthInsuranceNumber());
        patient.setHealthInsuranceVal(dto.getHealthInsuranceVal());
        patient.setZipcode(dto.getZipcode());
        patient.setStreet(dto.getStreet());
        patient.setAddressNumber(dto.getAddressNumber());
        patient.setComplement(dto.getComplement());
        patient.setReferencePoint(dto.getReferencePoint());
        patient.setNeighborhood(dto.getNeighborhood());
        patient.setCity(dto.getCity());
        patient.setState(dto.getState());
        patient.setUser(user);
    }

    public MedicalRecordListResponseDTO toDTO(Patient patient) {
        MedicalRecordListResponseDTO dto = new MedicalRecordListResponseDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setName(patient.getName());
        dto.setInsurance(patient.getHealthInsurance());
        return dto;
    }

}
