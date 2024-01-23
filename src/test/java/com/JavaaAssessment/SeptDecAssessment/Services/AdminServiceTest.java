package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.AdminDetails;
import com.JavaaAssessment.SeptDecAssessment.Repository.Admin.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    private AdminService adminService;
    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        adminService = new AdminService(adminRepository);
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void addAdmin() {

        //given
        AdminDetails adminDetails = new AdminDetails(
                "username",
                "password"
        );

        //when
        adminService.addAdmin(adminDetails);

        //then
        ArgumentCaptor<AdminDetails> adminDetailsArgumentCaptor = ArgumentCaptor.forClass(AdminDetails.class);
        Mockito.verify(adminRepository).save(adminDetailsArgumentCaptor.capture());

        AdminDetails capturedAdminDetails = adminDetailsArgumentCaptor.getValue();
        assertEquals(capturedAdminDetails.getUserName(), adminDetails.getUserName());
    }

    @Test
    void canLoadUserByUsername() {
        //given
        AdminDetails adminDetails = new AdminDetails(
                "username",
                "password"
        );
        
    }
}