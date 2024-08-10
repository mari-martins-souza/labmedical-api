package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;

import tech.lab365.labmedical.dtos.ExamResponseDTO;
import tech.lab365.labmedical.dtos.ExamRequestDTO;
import tech.lab365.labmedical.entities.Exam;
import tech.lab365.labmedical.entities.Patient;

@Component
public class ExamMapper {
    public Exam toExam(ExamRequestDTO dto, Patient patient) {
        Exam exam = new Exam();
        exam.setExam(dto.getExam());
        exam.setExamDate(dto.getExamDate());
        exam.setExamTime(dto.getExamTime());
        exam.setExamType(dto.getExamType());
        exam.setLab(dto.getLab());
        exam.setDocUrl(dto.getDocUrl());
        exam.setResult(dto.getResult());
        exam.setPatient(patient);
        return exam;
    }

    public ExamResponseDTO toResponseDTO (Exam exam) {
        ExamResponseDTO dto = new ExamResponseDTO();
        Patient patient = exam.getPatient();
        dto.setExamId(exam.getExamId());
        dto.setPatientName(patient.getName());
        dto.setId(patient.getId());
        dto.setExam(exam.getExam());
        dto.setExamDate(exam.getExamDate());
        dto.setExamTime(exam.getExamTime());
        dto.setExamType(exam.getExamType());
        dto.setLab(exam.getLab());
        dto.setDocUrl(exam.getDocUrl());
        dto.setResult(exam.getResult());
        return dto;
    }

    public void updateExamFromDTO(Exam exam, ExamRequestDTO dto) {
        exam.setExam(dto.getExam());
        exam.setExamDate(dto.getExamDate());
        exam.setExamTime(dto.getExamTime());
        exam.setExamType(dto.getExamType());
        exam.setLab(dto.getLab());
        exam.setDocUrl(dto.getDocUrl());
        exam.setResult(dto.getResult());
    }

}
