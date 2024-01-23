package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import com.JavaaAssessment.SeptDecAssessment.Services.CustomerService;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CliControllerTest {

    @Mock
    private FoodProductService productService;
    @Mock
    private FoodProductRepository foodProductRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerService customerService;

    private CliController cliController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliController = new CliController(productService, foodProductRepository, customerRepository, customerService);
    }

    @Test
    void showMenu_ShouldReturnValidOption() {
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int option = cliController.showMenu();

        assertEquals(3, option);
    }

    @Test
    void createProduct_ShouldCreateProductSuccessfully() {
        String input = "Test Description\n10.99\nTEST123\nTestCategory\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(productService.addFoodProduct(any(FoodProduct.class))).thenReturn(true);

        cliController.createProduct();

        verify(productService).addFoodProduct(argThat(foodProduct ->
                foodProduct.getDescription().equals("Test Description") &&
                        foodProduct.getPrice() == 10.99 &&
                        foodProduct.getSKU().equals("TEST123") &&
                        foodProduct.getCategory().equals("TestCategory")));
    }

    @Test
    void createProduct_ShouldPrintFailureMessage() {
        String input = "Test Description\n10.99\nTEST123\nTestCategory\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(productService.addFoodProduct(any(FoodProduct.class))).thenReturn(false);

        cliController.createProduct();

        verify(productService).addFoodProduct(any(FoodProduct.class));
        // You might want to check the output in the console. If there's an expected message, you can assert that.
    }

}
