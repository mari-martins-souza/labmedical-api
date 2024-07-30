package tech.lab365.labmedical.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.lab365.labmedical.dtos.ExamResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import tech.lab365.labmedical.dtos.ExamRequestDTO;
import tech.lab365.labmedical.entities.Exam;
import tech.lab365.labmedical.entities.Patient;
import tech.lab365.labmedical.mappers.ExamMapper;
import tech.lab365.labmedical.repositories.ExamRepository;
import tech.lab365.labmedical.repositories.PatientRepository;

@Service
public class ExamService {

    private final PatientRepository patientRepository;
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Autowired
    public ExamService(PatientRepository patientRepository, ExamRepository examRepository, ExamMapper examMapper) {
        this.patientRepository = patientRepository;
        this.examRepository = examRepository;
        this.examMapper = examMapper;
    }

    public ExamResponseDTO registerExam(ExamRequestDTO examRequestDTO) {
        Patient patient = patientRepository.findById(examRequestDTO.getPatient_id()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Exam exam = examMapper.toExam(examRequestDTO, patient);

        Exam savedExam = examRepository.save(exam);

        return examMapper.toResponseDTO(savedExam);
    }

    public ExamResponseDTO getExam(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Exam not found"));
        return examMapper.toResponseDTO(exam);
    }

    public ExamResponseDTO updateExam(Long id, ExamRequestDTO examRequestDTO) throws BadRequestException {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Exam not found"));

        if (!exam.getPatient().getPatientId().equals(examRequestDTO.getPatient_id())) {
            throw new BadRequestException("Patient ID in the request does not match the Patient ID in the existing exam");
        }

        examMapper.updateExamFromDTO(exam, examRequestDTO);

        Exam savedExam = examRepository.save(exam);

        return examMapper.toResponseDTO(savedExam);
    }

    public void deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new EntityNotFoundException("Exam not found");
        }
        examRepository.deleteById(id);
    }

}
