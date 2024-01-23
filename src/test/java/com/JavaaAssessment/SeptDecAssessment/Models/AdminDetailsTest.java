package com.JavaaAssessment.SeptDecAssessment.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminDetailsTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        AdminDetails adminDetails = new AdminDetails();

        // Assert
        assertNotNull(adminDetails);
        assertNull(adminDetails.getUserName());
        assertNull(adminDetails.getPassword());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        String userName = "admin";
        String password = "admin123";

        // Act
        AdminDetails adminDetails = new AdminDetails(userName, password);

        // Assert
        assertNotNull(adminDetails);
        assertEquals(userName, adminDetails.getUserName());
        assertEquals(password, adminDetails.getPassword());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        AdminDetails adminDetails = new AdminDetails();
        Long adminId = 1L;
        String userName = "admin";
        String password = "admin123";

        // Act
        adminDetails.setUserName(userName);
        adminDetails.setPassword(password);

        // Assert
        assertEquals(userName, adminDetails.getUserName());
        assertEquals(password, adminDetails.getPassword());
    }
}
