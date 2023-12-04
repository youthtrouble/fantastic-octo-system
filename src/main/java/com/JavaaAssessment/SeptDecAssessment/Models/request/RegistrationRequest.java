package com.JavaaAssessment.SeptDecAssessment.Models.request;

public class RegistrationRequest {
    private String email;
    private String userName;
    private String password;
    private String businessName;
    private String telephoneNumber;
    private String country;
    private String postCode;

    public RegistrationRequest(String email, String userName, String password, String businessName, String telephoneNumber, String country, String postCode) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
        this.postCode = postCode;
    }

    public RegistrationRequest() {

    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPassword() {
        return password;
    }
}
