package tech.lab365.labmedical.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.lab365.labmedical.validation.Cpf;
import tech.lab365.labmedical.validation.PhoneNumber;

import java.time.LocalDate;

public class PatientRequestDTO {

    @NotBlank
    @Size(min = 8, max = 64)
    @Column(nullable = false, length = 64)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String gender;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthdate;

    @NotBlank
    @Cpf
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String rg;

    @NotBlank
    @Column(nullable = false)
    private String issOrg;

    @NotBlank
    @Column(nullable = false)
    private String maritalStatus;

    @NotBlank
    @PhoneNumber
    @Column(nullable = false)
    private String phone;

    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(min = 3, max = 64)
    @Column(nullable = false, length = 64)
    private String placeOfBirth;

    @NotBlank
    @Column(nullable = false)
    private String emergCont;

    @NotBlank
    @PhoneNumber
    @Column(nullable = false)
    private String emergContNumber;

    private String listOfAllergies;

    private String careList;

    @NotBlank
    @Column(nullable = false)
    private String healthInsurance;

    private String healthInsuranceNumber;

    private LocalDate healthInsuranceVal;

    @NotBlank
    @Column(nullable = false)
    private String zipcode;

    private String street;

    private String addressNumber;

    private String complement;

    private String referencePoint;

    private String neighborhood;

    private String city;

    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getIssOrg() {
        return issOrg;
    }

    public void setIssOrg(String issOrg) {
        this.issOrg = issOrg;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public LocalDate getHealthInsuranceVal() {
        return healthInsuranceVal;
    }

    public void setHealthInsuranceVal(LocalDate healthInsuranceVal) {
        this.healthInsuranceVal = healthInsuranceVal;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
