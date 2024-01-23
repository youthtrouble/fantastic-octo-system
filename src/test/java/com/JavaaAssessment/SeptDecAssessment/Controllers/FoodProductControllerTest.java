package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FoodProductControllerTest {

    @Mock
    private FoodProductService productService;

    @InjectMocks
    private FoodProductController foodProductController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        foodProductController = new FoodProductController(productService);
    }

    @Test
    void testGetAllProducts() {
        List<FoodProduct> products = Arrays.asList(
                new FoodProduct("vibes1", "Product1", "Description1", 10.0),
                new FoodProduct("vibes2", "Product2", "Description2", 20.0)
        );

        when(productService.findAllProducts()).thenReturn(products);

        List<FoodProduct> result = foodProductController.getAllProducts();

        assertEquals(products, result);
    }

    @Test
    void testAddProduct() {
        FoodProduct product = new FoodProduct("vibes", "Product1", "Description1", 10.0);

        when(productService.addFoodProduct(any(FoodProduct.class))).thenReturn(true);

        ResponseEntity<FoodProduct> response = foodProductController.addProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        FoodProduct product = new FoodProduct("productId", "Product1", "Description1", 10.0);

        when(productService.findProduct(productId)).thenReturn(product);

        FoodProduct result = foodProductController.getProductById(productId);

        assertEquals(product, result);
    }

    @Test
    void testGetProductByCategory() {
        String category = "Category1";
        List<FoodProduct> products = Arrays.asList(
                new FoodProduct("insha", "Product1", "Description1", 10.0),
                new FoodProduct("vibes", "Product2", "Description2", 20.0)
        );

        when(productService.findProductByCategory(category)).thenReturn(products);

        List<FoodProduct> result = foodProductController.getProductByCategory(category);

        assertEquals(products, result);
    }
}
