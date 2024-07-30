package tech.lab365.labmedical.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="appointments")
public class Appointment {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;

    @NotBlank
    @Column(nullable = false)
    private String patientName;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long appointment_id;

    @NotBlank
    @Size(min = 8, max = 64)
    @Column(nullable = false, length = 64)
    private String reason;

    @NotNull
    @Column
    private LocalDate consultDate;

    @NotNull
    @Column
    private LocalTime consultTime;

    @NotBlank
    @Size(min = 16, max = 1024)
    @Column(nullable = false, length = 1024)
    private String problemDescrip;

    @Column
    private String prescMed;

    @Size(min = 16, max = 256)
    @Column(length = 256)
    private String dosagesPrec;

    public Appointment() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public @NotBlank @Size(min = 8, max = 64) String getReason() {
        return reason;
    }

    public void setReason(@NotBlank @Size(min = 8, max = 64) String reason) {
        this.reason = reason;
    }

    public @NotNull LocalDate getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(@NotNull LocalDate consultDate) {
        this.consultDate = consultDate;
    }

    public @NotNull LocalTime getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(@NotNull LocalTime consultTime) {
        this.consultTime = consultTime;
    }

    public @NotBlank @Size(min = 16, max = 1024) String getProblemDescrip() {
        return problemDescrip;
    }

    public void setProblemDescrip(@NotBlank @Size(min = 16, max = 1024) String problemDescrip) {
        this.problemDescrip = problemDescrip;
    }

    public String getPrescMed() {
        return prescMed;
    }

    public void setPrescMed(String prescMed) {
        this.prescMed = prescMed;
    }

    public @Size(min = 16, max = 256) String getDosagesPrec() {
        return dosagesPrec;
    }

    public void setDosagesPrec(@Size(min = 16, max = 256) String dosagesPrec) {
        this.dosagesPrec = dosagesPrec;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }
}
