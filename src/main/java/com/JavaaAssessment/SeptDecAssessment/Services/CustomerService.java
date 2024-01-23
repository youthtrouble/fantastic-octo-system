package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code CustomerService} class provides services related to customer operations,
 * such as adding, deleting, updating, and retrieving customer information.
 *
 * @author Deji Ajibola
 * @version 1.0
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Constructs a new {@code CustomerService} instance with the specified {@code CustomerRepository}.
     *
     * @param customerRepository The repository for managing customer data.
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds a new customer to the repository.
     *
     * @param customer The customer to be added.
     * @return {@code true} if the customer is successfully added, {@code false} otherwise.
     */
    public boolean addCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            return false;
        }
    }

    /**
     * Deletes a customer with the specified ID from the repository.
     *
     * @param customerId The ID of the customer to be deleted.
     * @return {@code true} if the customer is successfully deleted, {@code false} otherwise.
     */
    public boolean deleteCustomer(String customerId) {
        // Retrieve the customer from the repository or throw an exception if not found
        Customer customer = customerRepository.findCustomerByCustomerId(customerId).orElseThrow(
                () -> new IllegalStateException("Customer not found")
        );

        // Delete the customer from the repository
        customerRepository.delete(customer);

        return true;
    }

    /**
     * Updates the information of an existing customer in the repository.
     *
     * @param customerId      The ID of the customer to be updated.
     * @param businessName    The new business name (null or empty to keep existing value).
     * @param telePhoneNumber The new telephone number (null or empty to keep existing value).
     * @param country          The new country (null or empty to keep existing value).
     * @param postCode         The new postal code (null or empty to keep existing value).
     * @return {@code true} if the customer is successfully updated, {@code false} otherwise.
     */
    public boolean updateCustomer(String customerId, String businessName, String telePhoneNumber,
                                  String country, String postCode) {
        // Retrieve the customer from the repository or throw an exception if not found
        Optional<Customer> customer = customerRepository.findCustomerByCustomerId(customerId);

       if (customer.isPresent()) {
//             Update customer information if new values are provided
            if (businessName != null && !businessName.isEmpty() && !businessName.equals(customer.get().getBusinessName())) {
                customer.get().setBusinessName(businessName);
            }

            if (telePhoneNumber != null && !telePhoneNumber.isEmpty() && !telePhoneNumber.equals(customer.get().getTelephoneNumber())) {
                customer.get().setTelephoneNumber(telePhoneNumber);
            }

            if (country != null && !country.isEmpty() && !country.equals(customer.get().getAddress().getCountry())) {
                customer.get().getAddress().setCountry(country);
            }

            if (postCode != null && !postCode.isEmpty() && !postCode.equals(customer.get().getAddress().getPostCode())) {
                customer.get().getAddress().setPostCode(postCode);
            }
            // Save the updated customer back to the repository
            Customer deets = customerRepository.save(customer.get());
            System.out.println("deets saved = " + deets);

            return true;
       }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
    }

    /**
     * Retrieves a list of all customers from the repository.
     *
     * @return A list of all customers.
     */
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer with the specified ID from the repository.
     *
     * @param id The ID of the customer to be retrieved.
     * @return The customer with the specified ID.
     * @throws IllegalStateException if the customer is not found.
     */
    public Customer findCustomerById(String id) {
        return customerRepository.findCustomerById(id).orElseThrow(
                () -> new IllegalStateException("Customer not found")
        );
    }
}
