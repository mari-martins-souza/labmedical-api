package tech.lab365.labmedical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private UserMapper userMapper;

    public Patient registerPatient(PatientRequestDTO patientRequestDTO) {

        User user = userMapper.toUser(patientRequestDTO);
        userRepository.save(user);

        Patient patient = patientMapper.toPatient(patientRequestDTO, user);
        patientRepository.save(patient);

        return patient;
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toResponseDTO(patient);
    }

    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Patient patient;

        User user = userMapper.toUser(patientRequestDTO);
        userRepository.save(user);

        patient = patientMapper.toPatient(patientRequestDTO, user);
        patientRepository.save(patient);

        return patientMapper.toResponseDTO(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}