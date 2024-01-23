package com.JavaaAssessment.SeptDecAssessment.Models;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodProductTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        FoodProduct foodProduct = new FoodProduct();

        // Assert
        assertNotNull(foodProduct);
        assertNull(foodProduct.getId());
        assertNull(foodProduct.getSKU());
        assertNull(foodProduct.getCategory());
        assertNull(foodProduct.getDescription());
        assertEquals(0.0, foodProduct.getPrice());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Long id = 1L;
        String SKU = "TestSKU";
        String category = "TestCategory";
        String description = "TestDescription";
        double price = 19.99;

        // Act
        FoodProduct foodProduct = new FoodProduct(id, SKU, category, description, price);

        // Assert
        assertNotNull(foodProduct);
        assertEquals(id, foodProduct.getId());
        assertEquals(SKU, foodProduct.getSKU());
        assertEquals(category, foodProduct.getCategory());
        assertEquals(description, foodProduct.getDescription());
        assertEquals(price, foodProduct.getPrice());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        FoodProduct foodProduct = new FoodProduct();
        Long id = 1L;
        String SKU = "TestSKU";
        String category = "TestCategory";
        String description = "TestDescription";
        double price = 19.99;

        // Act
        foodProduct.setId(id);
        foodProduct.setSKU(SKU);
        foodProduct.setCategory(category);
        foodProduct.setDescription(description);
        foodProduct.setPrice(price);

        // Assert
        assertEquals(id, foodProduct.getId());
        assertEquals(SKU, foodProduct.getSKU());
        assertEquals(category, foodProduct.getCategory());
        assertEquals(description, foodProduct.getDescription());
        assertEquals(price, foodProduct.getPrice());
    }

    @Test
    void testToString() {
        // Arrange
        FoodProduct foodProduct = new FoodProduct(1L, "TestSKU", "TestCategory", "TestDescription", 19.99);

        // Act
        String result = foodProduct.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("TestSKU"));
        assertTrue(result.contains("TestCategory"));
        assertTrue(result.contains("TestDescription"));
        assertTrue(result.contains("19.99"));
    }
}
