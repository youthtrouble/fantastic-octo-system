package com.JavaaAssessment.SeptDecAssessment.Repository.Customer;

import com.JavaaAssessment.SeptDecAssessment.Models.Address;
import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void findByBusinessName() {
        //given
        Customer customer = new Customer(
                "businessName",
                "07383928816",
                "England",
                new Address(
                        "street",
                        "city",
                        "county",
                        "England",
                        "M11 1EF"
                ),
                "M11 1EF"
        );

        //when
        customerRepository.save(customer);
        Customer expected = customerRepository.findByBusinessName(customer.getBusinessName()).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        //then
        assertEquals(expected.getAddress(), customer.getAddress());
        assertEquals(expected.getBusinessName(), customer.getBusinessName());
        assertEquals(expected.getAddress().getCountry(), customer.getAddress().getCountry());
    }

    @Test
    void findCustomerById() {
        //given
        Customer customer = new Customer(
                "businessName",
                "07383928816",
                "England",
                new Address(
                        "street",
                        "city",
                        "county",
                        "England",
                        "M11 1EF"
                ),
                "M11 1EF"
        );

        //when
        customerRepository.save(customer);
        Customer expected = customerRepository.findCustomerById(String.valueOf(customer.getCustomerId())).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        //then
        assertEquals(expected.getAddress(), customer.getAddress());
        assertEquals(expected.getBusinessName(), customer.getBusinessName());
        assertEquals(expected.getAddress().getCountry(), customer.getAddress().getCountry());
    }

}