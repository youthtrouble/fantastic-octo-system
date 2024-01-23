package com.JavaaAssessment.SeptDecAssessment.Models.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginReqTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        LoginReq loginReq = new LoginReq(username, password);

        // Assert
        assertNotNull(loginReq);
        assertEquals(username, loginReq.getUsername());
        assertEquals(password, loginReq.getPassword());
    }

    @Test
    void testSetters() {
        // Arrange
        LoginReq loginReq = new LoginReq("initialUser", "initialPassword");
        String newUsername = "updatedUser";
        String newPassword = "updatedPassword";

        // Act
        loginReq.setUsername(newUsername);
        loginReq.setPassword(newPassword);

        // Assert
        assertEquals(newUsername, loginReq.getUsername());
        assertEquals(newPassword, loginReq.getPassword());
    }
}
