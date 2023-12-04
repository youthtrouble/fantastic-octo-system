package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDao implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerDao(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        Customer user = customerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalStateException("User not found")
        );
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        UserDetails userDetails =
                User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}
