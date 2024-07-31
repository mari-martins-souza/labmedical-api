package tech.lab365.labmedical.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExamResponseDTO {

    private Long patient_id;
    private String patientName;
    private Long exam_id;
    private String exam;
    private LocalDate examDate;
    private LocalTime examTime;
    private String examType;
    private String lab;
    private String docUrl;
    private String result;

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public LocalTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalTime examTime) {
        this.examTime = examTime;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
