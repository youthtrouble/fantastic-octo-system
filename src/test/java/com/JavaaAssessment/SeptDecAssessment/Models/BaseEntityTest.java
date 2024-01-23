package com.JavaaAssessment.SeptDecAssessment.Models;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        BaseEntity baseEntity = new BaseEntity();

        // Assert
        assertNotNull(baseEntity);
        assertNull(baseEntity.getCreatedAt());
        assertNull(baseEntity.getUpdatedAt());
        assertFalse(baseEntity.isDeleted());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Date createdAt = new Date();
        Date updatedAt = new Date();

        // Act
        BaseEntity baseEntity = new BaseEntity(createdAt, updatedAt);

        // Assert
        assertNotNull(baseEntity);
        assertEquals(createdAt, baseEntity.getCreatedAt());
        assertEquals(updatedAt, baseEntity.getUpdatedAt());
        assertFalse(baseEntity.isDeleted());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        BaseEntity baseEntity = new BaseEntity();
        Date createdAt = new Date();
        Date updatedAt = new Date();
        boolean isDeleted = true;

        // Act
        baseEntity.setCreatedAt(createdAt);
        baseEntity.setUpdatedAt(updatedAt);
        ReflectionTestUtils.setField(baseEntity, "isDeleted", isDeleted);

        // Assert
        assertEquals(createdAt, baseEntity.getCreatedAt());
        assertEquals(updatedAt, baseEntity.getUpdatedAt());
        assertTrue(baseEntity.isDeleted());
    }
}
