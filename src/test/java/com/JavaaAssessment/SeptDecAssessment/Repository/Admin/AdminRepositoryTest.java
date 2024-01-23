package com.JavaaAssessment.SeptDecAssessment.Repository.Admin;

import com.JavaaAssessment.SeptDecAssessment.Models.AdminDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void checkAdmin() {
        //given
        AdminDetails adminDetails = new AdminDetails(
                "admin",
                "admin"
        );
        adminRepository.save(adminDetails);

        //when
        boolean expected = adminRepository.checkAdmin(adminDetails.getUserName());

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void getAdminByUsername() {
        //given
        AdminDetails adminDetails = new AdminDetails(
                "admin",
                "admin"
        );
        adminRepository.save(adminDetails);

        //when
        AdminDetails expected = adminRepository.getAdminByUsername(adminDetails.getUserName()).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        //then
        assertThat(expected).isEqualTo(adminDetails);
    }
}