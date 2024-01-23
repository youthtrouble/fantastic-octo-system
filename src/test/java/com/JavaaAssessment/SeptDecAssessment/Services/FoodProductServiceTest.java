package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FoodProductServiceTest {

    @Mock
    private FoodProductRepository foodProductRepository;

    @InjectMocks
    private FoodProductService foodProductService;

    @Test
    void testAddFoodProduct() {
        // Create a sample food product
        FoodProduct sampleProduct = new FoodProduct();
        sampleProduct.setId(1L);
        sampleProduct.setSKU("SKU123");
        sampleProduct.setCategory("Beverage");
        sampleProduct.setPrice(2.5);

        // Mock the behavior of the repository
        Mockito.when(foodProductRepository.save(any(FoodProduct.class)))
                .thenReturn(sampleProduct);

        // Call the method to be tested
        boolean result = foodProductService.addFoodProduct(sampleProduct);

        // Verify that the repository save method was called with the correct food product
        Mockito.verify(foodProductRepository).save(sampleProduct);

        // Verify that the method returns true
        assertTrue(result);
    }

    @Test
    void testDeleteFoodProduct() {
        // Mock the behavior of the repository to return an optional containing a sample food product
        Mockito.when(foodProductRepository.findProductById(1L))
                .thenReturn(Optional.of(new FoodProduct()));

        // Call the method to be tested
        boolean result = foodProductService.deleteFoodProduct(1L);

        // Verify that the repository delete method was called with the correct ID
        Mockito.verify(foodProductRepository).delete(Mockito.any());

        // Verify that the method returns true
        assertTrue(result);
    }

    @Test
    void testDeleteFoodProductNotFound() {
        // Mock the behavior of the repository to return an empty optional
        Mockito.when(foodProductRepository.findProductById(1L))
                .thenReturn(Optional.empty());

        // Call the method to be tested
        boolean result = foodProductService.deleteFoodProduct(1L);

        // Verify that the repository delete method was not called
        Mockito.verify(foodProductRepository, Mockito.never()).delete(Mockito.any());

        // Verify that the method returns false
        assertFalse(result);
    }

    @Test
    void testFindAllProducts() {
        // Create a sample list of food products
        List<FoodProduct> sampleProducts = Collections.singletonList(new FoodProduct());

        // Mock the behavior of the repository to return the sample list
        Mockito.when(foodProductRepository.findAll())
                .thenReturn(sampleProducts);

        // Call the method to be tested
        List<FoodProduct> result = foodProductService.findAllProducts();

        // Verify that the repository findAll method was called
        Mockito.verify(foodProductRepository).findAll();

        // Verify that the method returns the correct list
        assertEquals(sampleProducts, result);
    }

    @Test
    void testFindProduct() {
        // Create a sample food product
        FoodProduct sampleProduct = new FoodProduct();
        sampleProduct.setId(1L);

        // Mock the behavior of the repository to return an optional containing the sample food product
        Mockito.when(foodProductRepository.findProductById(1L))
                .thenReturn(Optional.of(sampleProduct));

        // Call the method to be tested
        FoodProduct result = foodProductService.findProduct(1L);

        // Verify that the repository findProductById method was called with the correct ID
        Mockito.verify(foodProductRepository).findProductById(1L);

        // Verify that the method returns the correct food product
        assertEquals(sampleProduct, result);
    }

    @Test
    void testFindProductByCategory() {
        // Create a sample list of food products
        List<FoodProduct> sampleProducts = Collections.singletonList(new FoodProduct());

        // Mock the behavior of the repository to return the sample list
        Mockito.when(foodProductRepository.findProductByCategory("Beverage"))
                .thenReturn(sampleProducts);

        // Call the method to be tested
        List<FoodProduct> result = foodProductService.findProductByCategory("Beverage");

        // Verify that the repository findProductByCategory method was called with the correct category
        Mockito.verify(foodProductRepository).findProductByCategory("Beverage");

        // Verify that the method returns the correct list
        assertEquals(sampleProducts, result);
    }
}
