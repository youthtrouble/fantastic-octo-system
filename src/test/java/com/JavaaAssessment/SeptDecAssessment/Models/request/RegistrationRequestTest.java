package com.JavaaAssessment.SeptDecAssessment.Models.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationRequestTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        RegistrationRequest registrationRequest = new RegistrationRequest(username, password);

        // Assert
        assertNotNull(registrationRequest);
        assertEquals(username, registrationRequest.getUsername());
        assertEquals(password, registrationRequest.getPassword());
    }

    @Test
    void testSetters() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("initialUser", "initialPassword");
        String newUsername = "updatedUser";
        String newPassword = "updatedPassword";

        // Act
        registrationRequest.setUsername(newUsername);
        registrationRequest.setPassword(newPassword);

        // Assert
        assertEquals(newUsername, registrationRequest.getUsername());
        assertEquals(newPassword, registrationRequest.getPassword());
    }

    @Test
    void testToString() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("testUser", "testPassword");

        // Act
        String result = registrationRequest.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("testUser"));
        assertTrue(result.contains("testPassword"));
    }
}
