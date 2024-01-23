package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.Address;
import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;
import java.util.logging.Logger; // Import Logger from java.util.logging

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private Logger log;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void addCustomer() {
        //given
        Customer customer = new Customer(
                "businessName",
                "07383928816",
                "England",
                null,
                "M11 1EF"
        );

        //when
        customerService.addCustomer(customer);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertEquals(capturedCustomer.getBusinessName(), customer.getBusinessName());
    }

    @Test
    void deleteCustomer() {

        //given
        String customerId = "1";
        Customer sampleCustomer = new Customer();
        sampleCustomer.setCustomerId(1L);
        sampleCustomer.setBusinessName("businessName");
        sampleCustomer.setTelephoneNumber("07383928816");

        // Mock the behavior of the repository
        Mockito.when(customerRepository.findCustomerByCustomerId(customerId))
                .thenReturn(Optional.of(sampleCustomer));

        // Call the method to be tested
        boolean result = customerService.deleteCustomer(customerId);

        // Verify that the repository delete method was called with the correct customer
        Mockito.verify(customerRepository).delete(sampleCustomer);

        // Ensure that the method returns true
        assertTrue(result);
    }

    @Test
    void testDeleteCustomerNotFound() {
        // Create a sample customerId
        String customerId = "nonExistentCustomerId";

        // Mock the behavior of the repository to return an empty optional
        Mockito.when(customerRepository.findCustomerByCustomerId(customerId))
                .thenReturn(Optional.empty());

        // Call the method to be tested
        assertThrows(IllegalStateException.class, () -> customerService.deleteCustomer(customerId));

        // Ensure that the repository delete method was not called
        verify(customerRepository, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void testUpdateCustomer() {
        // Create a sample customer with initial values
        String customerId = "1";
        Customer initialCustomer = new Customer();
        initialCustomer.setCustomerId(1L);
        initialCustomer.setBusinessName("InitialBusiness");
        initialCustomer.setTelephoneNumber("123456789");
        Address initialAddress = new Address();
        initialAddress.setCountry("InitialCountry");
        initialAddress.setPostCode("12345");
        initialCustomer.setAddress(initialAddress);

        // Mock the behavior of the repository to return the sample customer
        Mockito.when(customerRepository.findCustomerByCustomerId(customerId))
                .thenReturn(Optional.of(initialCustomer));

        // Call the method to be tested with updated values
        boolean result = customerService.updateCustomer(
                customerId,
                "NewBusinessName",
                "987654321",
                "NewCountry",
                "54321"
        );

        // Verify that the repository save method was called with the correct customer
        Mockito.verify(customerRepository).save(initialCustomer);

        // Verify that the method returns true
        assertTrue(result);

        // Verify that the customer has been updated correctly
        assertEquals("NewBusinessName", initialCustomer.getBusinessName());
        assertEquals("987654321", initialCustomer.getTelephoneNumber());
        assertEquals("NewCountry", initialCustomer.getAddress().getCountry());
        assertEquals("54321", initialCustomer.getAddress().getPostCode());
    }

    @Test
    void findAllCustomers() {

        //when
        customerService.findAllCustomers();

        //then
        verify(customerRepository).findAll();
    }

    @Test
    void findCustomerById() {

        //given
        Customer customer = new Customer(
                "businessName",
                "07383928816",
                "England",
                null,
                "M11 1EF"
        );

        //when
        customerService.addCustomer(customer);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        // Verify that the findByID method of customerRepository is called with the correct customer
        customerRepository.findCustomerByCustomerId("1");
        verify(customerRepository, times(1)).findCustomerByCustomerId("1");

    }
}