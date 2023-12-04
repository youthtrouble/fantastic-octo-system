package com.JavaaAssessment.SeptDecAssessment.Repository.Customer;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository
    extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.userName = ?1")
    Optional<Customer> findByUsername(String username);

    @Query("select c from Customer c where c.email = ?1")
    Optional<Customer> findByEmail(String email);

    @Query("select c from Customer c where c.businessName = ?1")
    Optional<Customer> findByBusinessName(String businessName);

    @Query ("select c from Customer c where c.customerId = ?1")
    Optional<Customer> findCustomerById(String id);

    @Query("select case when count(c) > 0 then true else false end from Customer c where c.email = ?1")
    Boolean existsByEmail(String email);

    @Query("select case when count(c) > 0 then true else false end from Customer c where c.userName = ?1")
    Boolean existsByUsername(String username);

}
