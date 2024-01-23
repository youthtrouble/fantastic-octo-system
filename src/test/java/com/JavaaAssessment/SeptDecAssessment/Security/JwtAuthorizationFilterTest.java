package com.JavaaAssessment.SeptDecAssessment.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class JwtAuthorizationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Test
    void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        // Mocking
        Claims mockClaims = Mockito.mock(Claims.class);
        Mockito.when(jwtUtil.resolveToken(request)).thenReturn("validToken");
        Mockito.when(jwtUtil.resolveClaims(request)).thenReturn(mockClaims);
        Mockito.when(jwtUtil.validateClaims(mockClaims)).thenReturn(true);
        Mockito.when(jwtUtil.getEmail(mockClaims)).thenReturn("user@example.com");

        // Mocking the response writer
        PrintWriter writer = Mockito.mock(PrintWriter.class);

        // Executing the filter
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verifying that the authentication is set in the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals("user@example.com", authentication.getPrincipal());

        // Verifying that filterChain.doFilter was called
        Mockito.verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Mocking
        Mockito.when(jwtUtil.resolveToken(request)).thenReturn("invalidToken");

        // Mocking the response writer
        PrintWriter writer = Mockito.mock(PrintWriter.class);

        // Executing the filter
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verifying that the authentication is not set in the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(null, authentication);

        // Verifying that filterChain.doFilter was called
        Mockito.verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_Exception() throws ServletException, IOException {
        // Mocking
        Mockito.when(jwtUtil.resolveToken(request)).thenThrow(new RuntimeException("Invalid token"));

        // Mocking the response writer
        PrintWriter writer = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Executing the filter
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verifying that the error details are written to the response
        Mockito.verify(response).setStatus(HttpStatus.FORBIDDEN.value());
        Mockito.verify(response).setContentType(MediaType.APPLICATION_JSON_VALUE);
        Mockito.verify(objectMapper).writeValue(any(PrintWriter.class), any(Map.class));

        // Verifying that filterChain.doFilter was called
        Mockito.verify(filterChain).doFilter(request, response);
    }
}
