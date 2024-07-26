package tech.lab365.labmedical.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.lab365.labmedical.validation.Cpf;
import tech.lab365.labmedical.validation.PhoneNumber;

import java.time.LocalDate;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long patient_id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Patient() {
    }

    public Long getPatientId() {
        return patient_id;
    }

    public @NotBlank @Size(min = 8, max = 64) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 8, max = 64) String name) {
        this.name = name;
    }

    public @NotBlank String getGender() {
        return gender;
    }

    public void setGender(@NotBlank String gender) {
        this.gender = gender;
    }

    public @NotNull LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank @Size(max = 20) String getRg() {
        return rg;
    }

    public void setRg(@NotBlank @Size(max = 20) String rg) {
        this.rg = rg;
    }

    public @NotBlank String getIssOrg() {
        return issOrg;
    }

    public void setIssOrg(@NotBlank String issOrg) {
        this.issOrg = issOrg;
    }

    public @NotBlank String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(@NotBlank String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public @NotBlank String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank String phone) {
        this.phone = phone;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 3, max = 64) String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(@NotBlank @Size(min = 3, max = 64) String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public @NotBlank String getEmergCont() {
        return emergCont;
    }

    public void setEmergCont(@NotBlank String emergCont) {
        this.emergCont = emergCont;
    }

    public @NotBlank String getEmergContNumber() {
        return emergContNumber;
    }

    public void setEmergContNumber(@NotBlank String emergContNumber) {
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

    public @NotBlank String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(@NotBlank String healthInsurance) {
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

    public @NotBlank String getZipcode() {
        return zipcode;
    }

    public void setZipcode(@NotBlank String zipcode) {
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

    public void setUser(User user) {
        this.user = user;
    }

}
