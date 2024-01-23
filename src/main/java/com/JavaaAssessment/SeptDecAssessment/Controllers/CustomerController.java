package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Customer Controller.
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Create a new customer.
     *
     * @param customer The customer object to be created.
     * @return The created customer object.
     */
    @PostMapping(path = "/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.created(null).body(customer);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a customer.
     *
     * @param customerId The id of the customer to be deleted.
     * @return True if the customer was deleted, false otherwise.
     */
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteCustomer(@PathVariable("id")  String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    /**
     * Update a customer.
     *
     * @param customerId     The id of the customer to be updated.
     * @param customer The customer body to be updated
     * @return True if the customer was updated, false otherwise.
     */
    @PostMapping(path = "/update/{id}")
    public boolean updateCustomer(@PathVariable("id") String customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer.getBusinessName(), customer.getTelephoneNumber(), customer.getAddress().getCountry(), customer.getAddress().getPostCode());
    }

    /**
     * Get all customers.
     *
     * @return A list of all customers.
     */
    @GetMapping(path = "/all")
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    /**
     * Get a customer by id.
     *
     * @param id The id of the customer to be retrieved.
     * @return The customer object.
     */
    @GetMapping(path = "/{id}")
    public Customer getCustomerById(@PathVariable("id") String id) {
        return customerService.findCustomerById(id);
    }
}
