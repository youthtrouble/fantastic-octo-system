package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

/**
 *Represents the address of a customer in the product application.
 * This class contains information about the customer's address.
 * <p>
 *     The {@code Address} class provides methods to access and modify the customer's address.
 *     It also includes annotations for database mapping and table configuration.
 *     </p>
 *
 *         The customer's address includes:
 *         <ul>
 *             <li>Address line 1</li>
 *             <li>Address line 2</li>
 *             <li>Address line 3</li>
 *             <li>Country</li>
 *             <li>Post code</li>
 *             </ul>
 *
 *             <p>
 *                 The {@code Address} class is annotated with {@code @Entity} to indicate that it is a JPA entity.
 *                 It is also annotated with {@code @Table} to specify the name of the database table for this entity.
 *                 </p>
 *                 <p>
 *                     The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 *                     </p>
 */
@Entity
@Table(name = "Addresses")
public class Address extends BaseEntity {

    @Id
    @Column(name="address_id")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long addressId;
    private String  addressLine1;
    private String  addressLine2;
    private String addressLine3;
    private String country;
    private String postCode;

    /**
     * Creates an instance of the {@code Address} class.
     */
    public Address() {
    }

    /**
     * Creates an instance of the {@code Address} class.
     * @param addressLine1 The address line 1 of the customer.
     * @param addressLine2 The address line 2 of the customer.
     * @param addressLine3 The address line 3 of the customer.
     * @param country The country of the customer.
     * @param postCode The post code of the customer.
     */
    public Address(String addressLine1, String addressLine2, String addressLine3, String country, String postCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.country = country;
        this.postCode = postCode;
    }

    /**
     * Creates an instance of the {@code Address} class.
     * @param addressId The address id of the customer.
     * @param addressLine1 The address line 1 of the customer.
     * @param addressLine2 The address line 2 of the customer.
     * @param addressLine3 The address line 3 of the customer.
     * @param country The country of the customer.
     * @param postCode The post code of the customer.
     */
    public Address(Long addressId, String addressLine1, String addressLine2, String addressLine3, String country, String postCode) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.country = country;
        this.postCode = postCode;
    }

    /**
     * Gets the address id of the customer.
     * @return The address id of the customer.
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * Gets the address line 1 of the customer.
     * @return The address line 1 of the customer.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Gets the address line 2 of the customer.
     * @return The address line 2 of the customer.
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Gets the address line 3 of the customer.
     * @return The address line 3 of the customer.
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Gets the country of the customer.
     * @return The country of the customer.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the post code of the customer.
     * @return The post code of the customer.
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the address id of the customer.
     * @param addressId The address id of the customer.
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * Sets the address line 1 of the customer.
     * @param addressLine1 The address line 1 of the customer.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Sets the address line 2 of the customer.
     * @param addressLine2 The address line 2 of the customer.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Sets the address line 3 of the customer.
     * @param postCode The address line 3 of the customer.
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Sets the address line 3 of the customer.
     * @param addressLine3 The address line 3 of the customer.
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * Sets the country of the customer.
     * @param country The country of the customer.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
