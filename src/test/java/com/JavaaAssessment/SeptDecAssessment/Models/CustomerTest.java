package com.JavaaAssessment.SeptDecAssessment.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        Customer customer = new Customer();

        // Assert
        assertNotNull(customer);
        assertNull(customer.getCustomerId());
        assertNull(customer.getBusinessName());
        assertNull(customer.getTelephoneNumber());
        assertNull(customer.getAddress());
        assertNull(customer.getRole());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Long customerId = 1L;
        String businessName = "Test Business";
        String telephoneNumber = "123456789";
        Address address = new Address("Street 1", "Street 2", "Street 3", "Country", "12345");

        // Act
        Customer customer = new Customer(customerId, businessName, telephoneNumber, "Country", address, "12345");

        // Assert
        assertNotNull(customer);
        assertEquals(customerId, customer.getCustomerId());
        assertEquals(businessName, customer.getBusinessName());
        assertEquals(telephoneNumber, customer.getTelephoneNumber());
        assertEquals(address, customer.getAddress());
        assertEquals("customer", customer.getRole());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Customer customer = new Customer();
        Long customerId = 1L;
        String businessName = "Test Business";
        String telephoneNumber = "123456789";
        Address address = new Address("Street 1", "Street 2", "Street 3", "Country", "12345");

        // Act
        customer.setCustomerId(customerId);
        customer.setBusinessName(businessName);
        customer.setTelephoneNumber(telephoneNumber);
        customer.setAddress(address);

        // Assert
        assertEquals(customerId, customer.getCustomerId());
        assertEquals(businessName, customer.getBusinessName());
        assertEquals(telephoneNumber, customer.getTelephoneNumber());
        assertEquals(address, customer.getAddress());
    }
}