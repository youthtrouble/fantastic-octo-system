package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

/**
 * Represents a customer in the product application.
 * This class contains information about the customer's business name, telephone number, and address.
 *
 * The {@code Customer} class provides methods to access and modify the customer's information.
 * It also includes annotations for database mapping and table configuration.
 *
 * The customer's information includes:
 *
 * <ul>
 *      <li>Business name</li>
 *      <li>Telephone number</li>
 *      <li>Address</li>
 * </ul>
 *
 * <p>
 * The {@code Customer} class is annotated with {@code @Entity} to indicate that it is a JPA entity.
 * It is also annotated with {@code @Table} to specify the name of the database table for this entity.
 * </p>
 *
 * <p>
 * The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 * </p>
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @Column(name="customer_id")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_sequence")
    private Long customerId;
    private String businessName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private String telephoneNumber;
    private String role;

    public Customer() {

    }

    public Customer(String businessName, String telephoneNumber, String country, Address address, String postCode) {
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.role = "customer";
    }

    public Customer(Long customerId, String businessName, String telephoneNumber, String country, Address address, String postCode) {
        this.customerId = customerId;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.role = "customer";
    }

    public Long getCustomerId() {
        return customerId;
    }


    public String getBusinessName() {
        return businessName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getRole() {
        return role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String toString() {
        return "Product{" +
                "id=" + customerId +
                ", Name='" + businessName + '\'' +
                ", addess='" + address + '\'' +
                ", telephone number='" + telephoneNumber + '\'' +
                '}';
    }

}
