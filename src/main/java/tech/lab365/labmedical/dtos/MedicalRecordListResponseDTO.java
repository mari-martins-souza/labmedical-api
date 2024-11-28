package tech.lab365.labmedical.dtos;

import java.util.UUID;

public class MedicalRecordListResponseDTO {

    private UUID id;
    private String name;
    private String healthInsurance;
    private String phone;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String insurance) {
        this.healthInsurance = insurance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
