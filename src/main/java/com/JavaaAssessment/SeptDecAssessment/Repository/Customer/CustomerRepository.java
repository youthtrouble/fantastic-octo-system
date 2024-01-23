package com.JavaaAssessment.SeptDecAssessment.Repository.Customer;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class for Customer
 * This class is used to perform CRUD operations on the Customer table
 * <p>
 * This interface extends the JpaRepository interface, which provides generic
 * CRUD operations and advanced querying capabilities for the Customer entity.
 * It is annotated with the {@link org.springframework.data.jpa.repository.JpaRepository}
 * annotation to indicate that it is a repository interface.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.JavaaAssessment.SeptDecAssessment.Models.Customer
 */
@Repository
public interface CustomerRepository
    extends JpaRepository<Customer, Long> {

    /**
     * This method is used to find a customer by business name
     *
     * @param businessName
     * @return Customer
     */
    @Query("select c from Customer c where c.businessName = ?1")
    Optional<Customer> findByBusinessName(String businessName);

    /**
     * This method is used to find a customer by customer id
     *
     * @param id
     * @return Customer
     */
    @Query ("select c from Customer c where c.customerId = ?1")
    Optional<Customer> findCustomerById(String id);

    /**
     * This method is used to find a customer by customer id
     *
     * @param customerId The id of the customer
     * @return Customer
     */
    @Query("select c from Customer c where c.customerId = ?1")
    Optional<Customer> findCustomerByCustomerId(String customerId);

    /**
     * This method is used to delete a customer by customer id
     *
     * @param customerId The id of the customer
     */
    @Query("delete from Customer c where c.customerId = ?1")
    void deleteCustomerByID(String customerId);


}
