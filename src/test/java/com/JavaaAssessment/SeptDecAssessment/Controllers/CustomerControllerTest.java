package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer("BusinessName", "PhoneNumber", "Country", null, "PostCode");

        when(customerService.addCustomer(any(Customer.class))).thenReturn(true);

        ResponseEntity<Customer> response = customerController.addCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testDeleteCustomer() {
        String customerId = "123";

        when(customerService.deleteCustomer(customerId)).thenReturn(true);

        boolean result = customerController.deleteCustomer(customerId);

        assertEquals(true, result);
    }

    @Test
    void testUpdateCustomer() {
        String customerId = "123";
        String businessName = "NewBusinessName";
        String postCode = "NewPostCode";
        String telephoneNumber = "NewTelephoneNumber";
        String country = "NewCountry";

        when(customerService.updateCustomer(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);
    }

    @Test
    void testGetCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer("BusinessName1", "PhoneNumber1", "Country1", null, "PostCode1"),
                new Customer("BusinessName2", "PhoneNumber2", "Country2", null, "PostCode2")
        );

        when(customerService.findAllCustomers()).thenReturn(customers);

        List<Customer> result = customerController.getCustomers();

        assertEquals(customers, result);
    }

    @Test
    void testGetCustomerById() {
        String customerId = "123";
        Customer customer = new Customer("BusinessName", "PhoneNumber", "Country", null, "PostCode");

        when(customerService.findCustomerById(customerId)).thenReturn(customer);

        Customer result = customerController.getCustomerById(customerId);

        assertEquals(customer, result);
    }
}
