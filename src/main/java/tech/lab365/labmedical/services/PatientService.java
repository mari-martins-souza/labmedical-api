package tech.lab365.labmedical.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.PatientGetAllResponseDTO;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.PatientResponseDTO;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.mappers.PatientMapper;
import tech.lab365.labmedical.mappers.UserMapper;
import tech.lab365.labmedical.repositories.PatientRepository;
import tech.lab365.labmedical.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;
    private final UserMapper userMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, PatientMapper patientMapper, UserMapper userMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
        this.userMapper = userMapper;
    }

    public Patient registerPatient(PatientRequestDTO patientRequestDTO) {

        User user = userMapper.toUser(patientRequestDTO);
        userRepository.save(user);

        Patient patient = patientMapper.toPatient(patientRequestDTO, user);
        patientRepository.save(patient);

        return patient;
    }

    public List<PatientGetAllResponseDTO> getAllPatients(String name, String phone, String email) {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponseAllDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toResponseDTO(patient);
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        User user = patient.getUser();
        userMapper.updateUserFromDto(patientRequestDTO, user);
        userRepository.save(user);

        patientMapper.updatePatientFromDto(patientRequestDTO, patient, user);
        patientRepository.save(patient);

        return patientMapper.toResponseDTO(patient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}