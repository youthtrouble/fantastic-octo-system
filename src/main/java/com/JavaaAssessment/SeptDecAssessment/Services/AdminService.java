package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.AdminDetails;
import com.JavaaAssessment.SeptDecAssessment.Repository.Admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for AdminDetails
 * This class is used to perform CRUD operations on the AdminDetails table
 * <p>
 * This class is annotated with the {@link org.springframework.stereotype.Service}
 * annotation to indicate that it is a service class.
 * </p>
 *
 * @see org.springframework.stereotype.Service
 */
@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    /**
     * Constructs a new {@code AdminService} instance.
     *
     * @param adminRepository The data access object for Admin entities.
     */
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * This method is used to check if the admin exists
     *
     * @param username The username of the admin
     * @return boolean
     */
    public UserDetails loadUserByUsername(String username) {
        AdminDetails user = adminRepository.getAdminByUsername(username).orElseThrow(
                () -> new IllegalStateException("User not found")
        );
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        UserDetails userDetails =
                User.builder()
                        .username(user.getUserName())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }

    /**
     * This method is used to check if the admin exists
     *
     * @param adminDetails
     * @return boolean
     */
    public boolean addAdmin(AdminDetails adminDetails) {
        adminRepository.save(adminDetails);
        return true;
    }

}
