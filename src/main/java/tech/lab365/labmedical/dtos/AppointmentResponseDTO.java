package tech.lab365.labmedical.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponseDTO {

    private Long id;
    private String patientName;
    private Long appointment_id;
    private String reason;
    private LocalDate consultDate;
    private LocalTime consultTime;
    private String problemDescrip;
    private String prescMed;
    private String dosagesPrec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(LocalDate consultDate) {
        this.consultDate = consultDate;
    }

    public LocalTime getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(LocalTime consultTime) {
        this.consultTime = consultTime;
    }

    public String getProblemDescrip() {
        return problemDescrip;
    }

    public void setProblemDescrip(String problemDescrip) {
        this.problemDescrip = problemDescrip;
    }

    public String getPrescMed() {
        return prescMed;
    }

    public void setPrescMed(String prescMed) {
        this.prescMed = prescMed;
    }

    public String getDosagesPrec() {
        return dosagesPrec;
    }

    public void setDosagesPrec(String dosagesPrec) {
        this.dosagesPrec = dosagesPrec;
    }
}
