package com.JavaaAssessment.SeptDecAssessment.Models.response;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        // Arrange
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String expectedMessage = "Test error message";

        // Act
        ErrorRes errorResponse = new ErrorRes(expectedStatus, expectedMessage);

        // Assert
        assertNotNull(errorResponse);
        assertEquals(expectedStatus, errorResponse.getHttpStatus());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetters() {
        // Arrange
        ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, "Initial message");
        HttpStatus newStatus = HttpStatus.NOT_FOUND;
        String newMessage = "Updated message";

        // Act
        errorResponse.setHttpStatus(newStatus);
        errorResponse.setMessage(newMessage);

        // Assert
        assertEquals(newStatus, errorResponse.getHttpStatus());
        assertEquals(newMessage, errorResponse.getMessage());
    }
}
