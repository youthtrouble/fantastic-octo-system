package com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FoodProductRepositoryTest {

    @Autowired
    private FoodProductRepository foodProductRepository;

    @AfterEach
    void tearDown() {
        foodProductRepository.deleteAll();
    }

    @Test
    void findProductById() {
        //given
        FoodProduct foodProduct = new FoodProduct(
                1l,
                "SKU",
                "category",
                "description",
                1.0
        );

        //when
        foodProductRepository.save(foodProduct);
        FoodProduct product = foodProductRepository.findProductById(foodProduct.getId()).orElseThrow(
                () -> new IllegalStateException("Product not found")
        );

        //then
        assertEquals(product.getCategory(), foodProduct.getCategory());
        assertEquals(product.getDescription(), foodProduct.getDescription());
        assertEquals(product.getPrice(), foodProduct.getPrice());
    }

    @Test
    void deleteFoodProductById() {
    }

    @Test
    void findProductByCategory() {

        //given
        FoodProduct foodProduct = new FoodProduct(
                "SKU",
                "category",
                "description",
                1.0
        );

        //when
        foodProductRepository.save(foodProduct);
        List<FoodProduct> product = foodProductRepository.findProductByCategory(foodProduct.getCategory());

        //then
        assertEquals(product.get(0).getCategory(), foodProduct.getCategory());
    }
}