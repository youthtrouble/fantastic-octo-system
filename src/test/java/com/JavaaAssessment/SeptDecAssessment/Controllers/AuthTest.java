package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.request.LoginReq;
import com.JavaaAssessment.SeptDecAssessment.Models.request.RegistrationRequest;
import com.JavaaAssessment.SeptDecAssessment.Models.response.ErrorRes;
import com.JavaaAssessment.SeptDecAssessment.Models.response.LoginRes;
import com.JavaaAssessment.SeptDecAssessment.Security.JwtUtil;
import com.JavaaAssessment.SeptDecAssessment.Services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthTest {
    @Mock
    private AdminService adminService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private Auth auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginSuccessful() {
        // Arrange
        LoginReq loginReq = new LoginReq("testUser", "testPassword");
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        String token = "testToken";
        LoginRes expectedLoginRes = new LoginRes(loginReq.getUsername(), token);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtil.createToken()).thenReturn(token);

        // Act
        ResponseEntity responseEntity = auth.login(loginReq);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testLoginFailedInvalidCredentials() {
        // Arrange
        LoginReq loginReq = new LoginReq("testUser", "invalidPassword");

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

        // Act
        ResponseEntity responseEntity = auth.login(loginReq);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);
        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorRes.getHttpStatus());
        assertEquals("Invalid username or password", errorRes.getMessage());
    }

    @Test
    void testLoginFailedException() {
        // Arrange
        LoginReq loginReq = new LoginReq("testUser", "testPassword");

        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity responseEntity = auth.login(loginReq);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);
        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorRes.getHttpStatus());
        assertEquals("Some error", errorRes.getMessage());
    }

    //TODO: Add tests for register method - come back to this
//    @Test
//    void testRegisterSuccessful() {
//        // Arrange
//        RegistrationRequest registrationRequest = new RegistrationRequest("username", "password");
//
//        // Mock the adminDAO and jwtUtil
//        when(jwtUtil.createToken()).thenReturn("testToken");
//
//        // Act
//        ResponseEntity response = auth.register(registrationRequest);
//
//        // Assert
//        // Verify that addAdmin method is called with the expected AdminDetails argument
//        verify(adminDAO).addAdmin(Mockito.argThat(actualAdmin ->
//                actualAdmin.getUsername().equals(registrationRequest.getUsername()) &&
//                        passwordEncoder.matches(registrationRequest.getPassword(), actualAdmin.getPassword())
//        ));
//
//        // Verify that jwtUtil.createToken is called
//        verify(jwtUtil).createToken();
//
//        // Check the response entity
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        LoginRes loginRes = (LoginRes) response.getBody();
//        assertNotNull(loginRes);
//        assertEquals("username", loginRes.getUserName());
//        assertEquals("testToken", loginRes.getToken());
//    }

    @Test
    void testRegisterMissingFields() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest();

        // Act
        ResponseEntity responseEntity = auth.register(registrationRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);
        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorRes.getHttpStatus());
        assertEquals("Missing required fields", errorRes.getMessage());
        verify(adminService, never()).addAdmin(any());
    }

    @Test
    void testRegisterShortPassword() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("testUser", "short");

        // Act
        ResponseEntity responseEntity = auth.register(registrationRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);
        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorRes.getHttpStatus());
        assertEquals("Password must be at least 8 characters long", errorRes.getMessage());
        verify(adminService, never()).addAdmin(any());
    }

    @Test
    void testRegisterException() {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("testUser", "testPassword");

        when(passwordEncoder.encode(registrationRequest.getPassword())).thenReturn("encodedPassword");
        doThrow(new RuntimeException("Some error")).when(adminService).addAdmin(any());

        // Act
        ResponseEntity responseEntity = auth.register(registrationRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);
        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorRes.getHttpStatus());
        assertEquals("Some error", errorRes.getMessage());
        verify(adminService, times(1)).addAdmin(any());
    }
}
