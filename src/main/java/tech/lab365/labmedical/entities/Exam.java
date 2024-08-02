package tech.lab365.labmedical.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="exams")
public class Exam {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Patient patient;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long exam_id;

    @NotBlank
    @Size(min = 8, max = 64)
    @Column(nullable = false, length = 64)
    private String exam;

    @NotNull
    @Column(nullable = false)
    private LocalDate examDate;

    @NotNull
    @Column(nullable = false)
    private LocalTime examTime;

    @NotBlank
    @Size(min = 4, max = 32)
    @Column(nullable = false, length = 32)
    private String examType;

    @NotBlank
    @Size(min = 4, max = 32)
    @Column(nullable = false, length = 32)
    private String lab;

    @Column
    private String docUrl;

    @Size(min = 16, max = 1024)
    @Column(length = 1024)
    private String result;

    public Exam() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public @NotBlank @Size(min = 8, max = 64) String getExam() {
        return exam;
    }

    public void setExam(@NotBlank @Size(min = 8, max = 64) String exam) {
        this.exam = exam;
    }

    public @NotNull LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(@NotNull LocalDate examDate) {
        this.examDate = examDate;
    }

    public @NotNull LocalTime getExamTime() {
        return examTime;
    }

    public void setExamTime(@NotNull LocalTime examTime) {
        this.examTime = examTime;
    }

    public @NotBlank @Size(min = 4, max = 32) String getExamType() {
        return examType;
    }

    public void setExamType(@NotBlank @Size(min = 4, max = 32) String examType) {
        this.examType = examType;
    }

    public @NotBlank @Size(min = 4, max = 32) String getLab() {
        return lab;
    }

    public void setLab(@NotBlank @Size(min = 4, max = 32) String lab) {
        this.lab = lab;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public @Size(min = 16, max = 1024) String getResult() {
        return result;
    }

    public void setResult(@Size(min = 16, max = 1024) String result) {
        this.result = result;
    }

}
