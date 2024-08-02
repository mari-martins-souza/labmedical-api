package tech.lab365.labmedical.dtos;

import java.util.List;

public class PatientMedicalRecordResponseDTO {

    private String name;
    private String healthInsurance;
    private String emergCont;
    private String emergContNumber;
    private String listOfAllergies;
    private String careList;
    private List<AppointmentResponseDTO> appointments;
    private List<ExamResponseDTO> exams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getEmergCont() {
        return emergCont;
    }

    public void setEmergCont(String emergCont) {
        this.emergCont = emergCont;
    }

    public String getEmergContNumber() {
        return emergContNumber;
    }

    public void setEmergContNumber(String emergContNumber) {
        this.emergContNumber = emergContNumber;
    }

    public String getListOfAllergies() {
        return listOfAllergies;
    }

    public void setListOfAllergies(String listOfAllergies) {
        this.listOfAllergies = listOfAllergies;
    }

    public String getCareList() {
        return careList;
    }

    public void setCareList(String careList) {
        this.careList = careList;
    }

    public List<AppointmentResponseDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentResponseDTO> appointments) {
        this.appointments = appointments;
    }

    public List<ExamResponseDTO> getExams() {
        return exams;
    }

    public void setExams(List<ExamResponseDTO> exams) {
        this.exams = exams;
    }
}
