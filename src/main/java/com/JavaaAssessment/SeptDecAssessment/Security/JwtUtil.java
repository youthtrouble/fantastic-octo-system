package com.JavaaAssessment.SeptDecAssessment.Security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The {@code JwtUtil} class is used to generate and validate JWT tokens.
 * <p>
 * This class is annotated with the {@link org.springframework.stereotype.Component}
 * annotation to indicate that it is a component class.
 * </p>
 *
 * @see org.springframework.stereotype.Component
 */
@Component
public class JwtUtil {

    private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60 * 60 * 1000;
    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    /**
     * Constructs a new {@code JwtUtil} instance.
     * <p>
     * This constructor initializes the {@code JwtParser} for parsing JWT tokens.
     * </p>
     */
    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    /**
     * Generates a JWT token with default claims and expiration time.
     *
     * @return String The generated JWT token.
     */
    public String createToken() {
        Claims claims = Jwts.claims().setSubject("ADMIN");
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Validates the claims of a JWT token, checking if the token is expired.
     *
     * @param claims The claims of the JWT token.
     * @return boolean {@code true} if the token is valid, {@code false} otherwise.
     * @throws AuthenticationException
     */
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    /**
     * Resolves the claims of a JWT token, checking if the token is expired.
     *
     * @param req The incoming request.
     * @return Claims The claims of the JWT token.
     * @throws AuthenticationException
     */
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    /**
     * Resolves the JWT token from the incoming request.
     *
     * @param request The incoming request.
     * @return String The resolved JWT token.
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * Retrieves the email from the claims of a JWT token.
     *
     * @param claims The claims of the JWT token.
     * @return String The email.
     */
    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    // (other methods...)

    /**
     * Converts an object to a map using reflection.
     *
     * @param object The object to be converted.
     * @return The map representation of the object.
     * @throws IllegalAccessException
     */
    public Map<String, String> convertUsingReflection(Object object) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), (String) field.get(object));
        }

        return map;
    }
}
