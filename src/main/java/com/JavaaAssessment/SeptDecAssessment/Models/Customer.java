package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @Column(name="customer_id")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long customerId;
    private String email;
    private String userName;
    private String password;
    private String businessName;
    private String telephoneNumber;
    private String country;
    private String postCode;

    public Customer() {

    }

    public Customer(String email, String userName, String password, String businessName, String telephoneNumber, String country, String postCode) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
        this.postCode = postCode;
    }

    public Customer(Long customerId, String email, String userName, String password, String businessName, String telephoneNumber, String country, String postCode) {
        this.customerId = customerId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
        this.postCode = postCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
