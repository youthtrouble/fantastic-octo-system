package com.JavaaAssessment.SeptDecAssessment.Security;

import com.JavaaAssessment.SeptDecAssessment.Services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class SecurityConfigTest {
    @Test
    void testPasswordEncoder() {
        // Arrange
        SecurityConfig securityConfig = new SecurityConfig(mock(AdminService.class), mock(JwtAuthorizationFilter.class));

        // Act
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        // Assert
        assertThat(passwordEncoder).isNotNull();
    }
}
