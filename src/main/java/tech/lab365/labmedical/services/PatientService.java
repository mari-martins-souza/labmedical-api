package tech.lab365.labmedical.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.MedicalRecordListResponseDTO;
import tech.lab365.labmedical.dtos.PatientGetAllResponseDTO;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.PatientResponseDTO;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.error.ConflictException;
import tech.lab365.labmedical.mappers.PatientMapper;
import tech.lab365.labmedical.mappers.UserMapper;
import tech.lab365.labmedical.repositories.AppointmentRepository;
import tech.lab365.labmedical.repositories.ExamRepository;
import tech.lab365.labmedical.repositories.PatientRepository;
import tech.lab365.labmedical.repositories.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;
    private final UserMapper userMapper;
    private final AppointmentRepository appointmentRepository;
    private final ExamRepository examRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, PatientMapper patientMapper, UserMapper userMapper, AppointmentRepository appointmentRepository, ExamRepository examRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
        this.userMapper = userMapper;
        this.appointmentRepository = appointmentRepository;
        this.examRepository = examRepository;
    }

    public Patient registerPatient(PatientRequestDTO patientRequestDTO) throws BadRequestException {

        if (patientRequestDTO.getName() == null || patientRequestDTO.getName().isEmpty()) {
            throw new BadRequestException("name is mandatory");
        }
        if (patientRequestDTO.getGender() == null || patientRequestDTO.getGender().isEmpty()) {
            throw new BadRequestException("gender is mandatory");
        }
        if (patientRequestDTO.getBirthdate() == null) {
            throw new BadRequestException("birthdate is mandatory");
        }
        if (patientRequestDTO.getCpf() == null || patientRequestDTO.getCpf().isEmpty()) {
            throw new BadRequestException("CPF is mandatory");
        }
        if (patientRequestDTO.getRg() == null || patientRequestDTO.getRg().isEmpty()) {
            throw new BadRequestException("RG is mandatory");
        }
        if (patientRequestDTO.getIssOrg() == null || patientRequestDTO.getIssOrg().isEmpty()) {
            throw new BadRequestException("issOrg is mandatory");
        }
        if (patientRequestDTO.getMaritalStatus() == null || patientRequestDTO.getMaritalStatus().isEmpty()) {
            throw new BadRequestException("marital status is mandatory");
        }
        if (patientRequestDTO.getPhone() == null || patientRequestDTO.getPhone().isEmpty()) {
            throw new BadRequestException("phone is mandatory");
        }
        if (patientRequestDTO.getEmail() == null || patientRequestDTO.getEmail().isEmpty()) {
            throw new BadRequestException("email is mandatory");
        }
        if (patientRequestDTO.getPlaceOfBirth() == null || patientRequestDTO.getPlaceOfBirth().isEmpty()) {
            throw new BadRequestException("place of birth is mandatory");
        }
        if (patientRequestDTO.getHealthInsurance() == null || patientRequestDTO.getHealthInsurance().isEmpty()) {
            throw new BadRequestException("insurance is mandatory");
        }
        if (patientRequestDTO.getZipcode() == null || patientRequestDTO.getZipcode().isEmpty()) {
            throw new BadRequestException("cep is mandatory");
        }

        User user = userMapper.toUser(patientRequestDTO);

        if (userRepository.existsByCpf(user.getCpf())) {
            throw new ConflictException("User/patient already exists");
        }

        userRepository.save(user);

        Patient patient = patientMapper.toPatient(patientRequestDTO, user);

        if (patientRepository.existsByCpf(patient.getCpf())) {
            throw new ConflictException("User/patient already exists");
        }

        patientRepository.save(patient);

        return patient;
    }

    public List<PatientGetAllResponseDTO> getAllPatients(String name, String phone, String email) {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponseAllDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatient(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return patientMapper.toResponseDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) throws BadRequestException {

        if (patientRequestDTO.getName() == null || patientRequestDTO.getName().isEmpty()) {
            throw new BadRequestException("name is mandatory");
        }
        if (patientRequestDTO.getGender() == null || patientRequestDTO.getGender().isEmpty()) {
            throw new BadRequestException("gender is mandatory");
        }
        if (patientRequestDTO.getBirthdate() == null) {
            throw new BadRequestException("birthdate is mandatory");
        }
        if (patientRequestDTO.getCpf() == null || patientRequestDTO.getCpf().isEmpty()) {
            throw new BadRequestException("CPF is mandatory");
        }
        if (patientRequestDTO.getRg() == null || patientRequestDTO.getRg().isEmpty()) {
            throw new BadRequestException("RG is mandatory");
        }
        if (patientRequestDTO.getIssOrg() == null || patientRequestDTO.getIssOrg().isEmpty()) {
            throw new BadRequestException("issOrg is mandatory");
        }
        if (patientRequestDTO.getMaritalStatus() == null || patientRequestDTO.getMaritalStatus().isEmpty()) {
            throw new BadRequestException("marital status is mandatory");
        }
        if (patientRequestDTO.getPhone() == null || patientRequestDTO.getPhone().isEmpty()) {
            throw new BadRequestException("phone is mandatory");
        }
        if (patientRequestDTO.getEmail() == null || patientRequestDTO.getEmail().isEmpty()) {
            throw new BadRequestException("email is mandatory");
        }
        if (patientRequestDTO.getPlaceOfBirth() == null || patientRequestDTO.getPlaceOfBirth().isEmpty()) {
            throw new BadRequestException("place of birth is mandatory");
        }
        if (patientRequestDTO.getHealthInsurance() == null || patientRequestDTO.getHealthInsurance().isEmpty()) {
            throw new BadRequestException("insurance is mandatory");
        }
        if (patientRequestDTO.getZipcode() == null || patientRequestDTO.getZipcode().isEmpty()) {
            throw new BadRequestException("cep is mandatory");
        }

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        User user = patient.getUser();
        userMapper.updateUserFromDto(patientRequestDTO, user);
        userRepository.save(user);

        patientMapper.updatePatientFromDto(patientRequestDTO, patient, user);
        patientRepository.save(patient);

        return patientMapper.toResponseDTO(patient);
    }

    public void deletePatient(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found");
        }

        boolean hasAppointment = appointmentRepository.existsByPatient_Id(id);
        boolean hasExam = examRepository.existsByPatient_Id(id);

        if(hasAppointment || hasExam) {
            throw new IllegalStateException("Cannot delete patient with appointments or exams");
        }
        patientRepository.deleteById(id);
    }

    public Page<MedicalRecordListResponseDTO> getAllMedicalRecords(String name, UUID id, Pageable pageable) {
        Page<Patient> patients = patientRepository.findByNameAndPatientId(name, id, pageable);

        if (patients.isEmpty()) {
            throw new EntityNotFoundException("No patients found");
        }

        return patients.map(patientMapper::toDTO);
    }
}