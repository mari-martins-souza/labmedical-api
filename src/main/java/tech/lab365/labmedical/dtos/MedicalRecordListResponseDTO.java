package tech.lab365.labmedical.dtos;

import java.util.UUID;

public class MedicalRecordListResponseDTO {

    private UUID id;
    private String name;
    private String insurance;

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

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
