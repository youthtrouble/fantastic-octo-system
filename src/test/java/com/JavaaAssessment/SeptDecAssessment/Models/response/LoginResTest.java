package com.JavaaAssessment.SeptDecAssessment.Models.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        String expectedUserName = "testUser";
        String expectedToken = "testToken";

        // Act
        LoginRes loginResponse = new LoginRes(expectedUserName, expectedToken);

        // Assert
        assertNotNull(loginResponse);
        assertEquals(expectedUserName, loginResponse.getUserName());
        assertEquals(expectedToken, loginResponse.getToken());
    }

    @Test
    void testSetters() {
        // Arrange
        LoginRes loginResponse = new LoginRes("initialUser", "initialToken");
        String newUserName = "updatedUser";
        String newToken = "updatedToken";

        // Act
        loginResponse.setUserName(newUserName);
        loginResponse.setToken(newToken);

        // Assert
        assertEquals(newUserName, loginResponse.getUserName());
        assertEquals(newToken, loginResponse.getToken());
    }
}
