package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.AdminDetails;
import com.JavaaAssessment.SeptDecAssessment.Security.JwtUtil;
import com.JavaaAssessment.SeptDecAssessment.Models.request.LoginReq;
import com.JavaaAssessment.SeptDecAssessment.Models.request.RegistrationRequest;
import com.JavaaAssessment.SeptDecAssessment.Models.response.ErrorRes;
import com.JavaaAssessment.SeptDecAssessment.Models.response.LoginRes;
import com.JavaaAssessment.SeptDecAssessment.Services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The {@code Auth} class is a controller class responsible for handling authentication-related requests.
 * <p>
 * This class is annotated with the {@link org.springframework.stereotype.Controller}
 * annotation to indicate that it is a Spring MVC controller.
 * </p>
 *
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.bind.annotation.RequestMapping
 */
@CrossOrigin
@Controller
@RequestMapping(path = "/api/v1/auth")
public class Auth {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructs a new {@code Auth} instance.
     *
     * @param jwtUtil               The JWT utility for token-related operations.
     * @param adminService              The data access object for Admin entities.
     * @param passwordEncoder       The password encoder for encoding passwords.
     * @param authenticationManager The authentication manager for authenticating users.
     */
    public Auth(JwtUtil jwtUtil, AdminService adminService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Encodes the raw password using the configured password encoder.
     *
     * @param rawPassword The raw password to be encoded.
     * @return String The encoded password.
     * @see org.springframework.security.crypto.password.PasswordEncoder
     */
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Handles the login request.
     *
     * @param loginReq The login request object.
     * @return ResponseEntity The response entity containing the login response object or an error response.
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        // authenticate user
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword())
                    );
            String email = authentication.getName();

            String token = jwtUtil.createToken();
            LoginRes loginRes = new LoginRes(email, token);
            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Handles the registration request.
     *
     * @param regReq The registration request object.
     * @return ResponseEntity The response entity containing the registration response object or an error response.
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequest regReq) {

        // ensure all required fields are present
        if (regReq.getUsername() == null || regReq.getPassword() == null) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Missing required fields");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // ensure password standard is met
        if (regReq.getPassword().length() < 8) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            AdminDetails admin = new AdminDetails(regReq.getUsername(), encodePassword(regReq.getPassword()));
            adminService.addAdmin(admin);

            // create token
            String token = jwtUtil.createToken();
            LoginRes loginRes = new LoginRes(regReq.getUsername(), token);

            return ResponseEntity.ok(loginRes);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
