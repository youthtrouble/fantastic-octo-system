package com.JavaaAssessment.SeptDecAssessment.Models;

import com.JavaaAssessment.SeptDecAssessment.Models.Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        Address address = new Address();

        // Assert
        assertNotNull(address);
        assertNull(address.getAddressId());
        assertNull(address.getAddressLine1());
        assertNull(address.getAddressLine2());
        assertNull(address.getAddressLine3());
        assertNull(address.getCountry());
        assertNull(address.getPostCode());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        String addressLine1 = "123 Main St";
        String addressLine2 = "Apt 456";
        String addressLine3 = "Suite 789";
        String country = "USA";
        String postCode = "12345";

        // Act
        Address address = new Address(addressLine1, addressLine2, addressLine3, country, postCode);

        // Assert
        assertNotNull(address);
        assertNull(address.getAddressId()); // Assuming addressId is not set in the constructor
        assertEquals(addressLine1, address.getAddressLine1());
        assertEquals(addressLine2, address.getAddressLine2());
        assertEquals(addressLine3, address.getAddressLine3());
        assertEquals(country, address.getCountry());
        assertEquals(postCode, address.getPostCode());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        Address address = new Address();
        Long addressId = 1L;
        String addressLine1 = "123 Main St";
        String addressLine2 = "Apt 456";
        String addressLine3 = "Suite 789";
        String country = "USA";
        String postCode = "12345";

        // Act
        address.setAddressId(addressId);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setAddressLine3(addressLine3);
        address.setCountry(country);
        address.setPostCode(postCode);

        // Assert
        assertEquals(addressId, address.getAddressId());
        assertEquals(addressLine1, address.getAddressLine1());
        assertEquals(addressLine2, address.getAddressLine2());
        assertEquals(addressLine3, address.getAddressLine3());
        assertEquals(country, address.getCountry());
        assertEquals(postCode, address.getPostCode());
    }
}
