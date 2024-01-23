package com.JavaaAssessment.SeptDecAssessment.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Test
    void testCreateToken() {
        // Act
        String token = jwtUtil.createToken();

        // Assert
        assertNotNull(token);
        Claims claims = jwtUtil.parseJwtClaims(token);
        assertEquals("ADMIN", claims.getSubject());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    @Test
    void testValidateClaims_ValidClaims() {
        // Arrange
        Claims claims = Jwts.claims().setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));

        // Act
        boolean result = jwtUtil.validateClaims(claims);

        // Assert
        assertTrue(result);
    }

    @Test
    void testValidateClaims_ExpiredClaims() {
        // Arrange
        Claims claims = Jwts.claims().setExpiration(new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10)));

        // Act and Assert
        assertFalse(jwtUtil.validateClaims(claims));
    }

    @Test
    void testResolveClaims_ValidToken() {
        // Arrange
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBRE1JTiIsImV4cCI6MTkxOTM2ODY0Mn0.y5dHEcWk8oQEnks99iFOwRnf0ngFrF1HU32nMuwQnuE";
        Mockito.when(jwtUtil.resolveToken(request)).thenReturn(token);

        // Act and Assert
        assertDoesNotThrow(() -> jwtUtil.resolveClaims(request));
    }

    @Test
    void testResolveToken() {
        // Arrange
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer validToken");

        // Act
        String result = jwtUtil.resolveToken(request);

        // Assert
        assertEquals("validToken", result);
    }

    @Test
    void testResolveToken_NullHeader() {
        // Arrange
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        String result = jwtUtil.resolveToken(request);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetEmail() {
        // Arrange
        Claims claims = Jwts.claims().setSubject("user@example.com");

        // Act
        String result = jwtUtil.getEmail(claims);

        // Assert
        assertEquals("user@example.com", result);
    }

    @Test
    void testConvertUsingReflection() throws IllegalAccessException {
        // Arrange
        TestObject testObject = new TestObject();
        testObject.setUsername("username");
        testObject.setEmail("user@example.com");

        // Act
        Map<String, String> result = jwtUtil.convertUsingReflection(testObject);

        // Assert
        assertNotNull(result);
        assertEquals("username", result.get("username"));
        assertEquals("user@example.com", result.get("email"));
    }

    private static class TestObject {
        private String username;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
