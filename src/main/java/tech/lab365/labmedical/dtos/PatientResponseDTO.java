package tech.lab365.labmedical.dtos;

import java.time.LocalDate;

public class PatientResponseDTO {

    private Long user_id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private String cpf;
    private String password;
    private String profile;

    private Long patient_id;
    private String gender;
    private String rg;
    private String issOrg;
    private String maritalStatus;
    private String phone;
    private String placeOfBirth;
    private String emergCont;
    private String emergContNumber;
    private String listOfAllergies;
    private String careList;
    private String healthInsurance;
    private String healthInsuranceNumber;
    private LocalDate healthInsuranceVal;
    private String zipcode;
    private String street;
    private String addressNumber;
    private String complement;
    private String referencePoint;
    private String neighborhood;
    private String city;
    private String state;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
