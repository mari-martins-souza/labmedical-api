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

    public ExamResponseDTO registerExam(ExamRequestDTO examRequestDTO) throws BadRequestException {
        Patient patient = patientRepository.findById(examRequestDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        if (examRequestDTO.getExamTime() == null) {
            throw new BadRequestException("examTime is mandatory");
        }

        if (examRequestDTO.getExamDate() == null) {
            throw new BadRequestException("examDate is mandatory");
        }

        if (examRequestDTO.getExamType() == null || examRequestDTO.getExamType().isEmpty()) {
            throw new BadRequestException("examType is mandatory");
        }

        if (examRequestDTO.getLab() == null || examRequestDTO.getLab().isEmpty()) {
            throw new BadRequestException("lab is mandatory");
        }

        if (examRequestDTO.getExam() == null || examRequestDTO.getExam().isEmpty()) {
            throw new BadRequestException("exam is mandatory");
        }

        if (examRequestDTO.getId() == null) {
            throw new BadRequestException("patient id is mandatory");
        }

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

        if (!exam.getPatient().getId().equals(examRequestDTO.getId())) {
            throw new BadRequestException("Patient ID in the request does not match the Patient ID in the existing exam");
        }

        if (examRequestDTO.getExamTime() == null) {
            throw new BadRequestException("examTime is mandatory");
        }

        if (examRequestDTO.getExamDate() == null) {
            throw new BadRequestException("examDate is mandatory");
        }

        if (examRequestDTO.getExamType() == null || examRequestDTO.getExamType().isEmpty()) {
            throw new BadRequestException("examType is mandatory");
        }

        if (examRequestDTO.getLab() == null || examRequestDTO.getLab().isEmpty()) {
            throw new BadRequestException("lab is mandatory");
        }

        if (examRequestDTO.getExam() == null || examRequestDTO.getExam().isEmpty()) {
            throw new BadRequestException("exam is mandatory");
        }

        if (examRequestDTO.getId() == null) {
            throw new BadRequestException("patient id is mandatory");
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
