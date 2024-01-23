package com.JavaaAssessment.SeptDecAssessment.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code JwtAuthorizationFilter} class is used to filter incoming requests,
 * checking for a valid JWT token and setting the authentication in the security context.
 * <p>
 * This class is annotated with the {@link org.springframework.stereotype.Component}
 * annotation to indicate that it is a component class.
 * </p>
 *
 * @see org.springframework.stereotype.Component
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    /**
     * Constructs a new {@code JwtAuthorizationFilter} instance.
     *
     * @param jwtUtil The JWT utility for token-related operations.
     * @param mapper  The object mapper for JSON processing.
     */
    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    /**
     * Filters incoming requests, checking for a valid JWT token and setting the authentication in the security context.
     *
     * @param request     The incoming request.
     * @param response    The outgoing response.
     * @param filterChain The filter chain.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            Claims claims = jwtUtil.resolveClaims(request);
            if (claims != null && jwtUtil.validateClaims(claims)) {
                String email = jwtUtil.getEmail(claims);
                Map<String, String> claimToValue = new HashMap<>();
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(email, claimToValue, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println("Exception from internal filter: " + e.getMessage());
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);

        }

        filterChain.doFilter(request, response);
    }
}
