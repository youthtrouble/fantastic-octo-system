package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Security.JwtUtil;
import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Models.request.LoginReq;
import com.JavaaAssessment.SeptDecAssessment.Models.request.RegistrationRequest;
import com.JavaaAssessment.SeptDecAssessment.Models.response.ErrorRes;
import com.JavaaAssessment.SeptDecAssessment.Models.response.LoginRes;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/v1/auth")
public class Auth {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    public Auth(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        // authenticate user
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            String email = authentication.getName();
            //find full user details by email
            Customer user = customerRepository.findByEmail(email).orElseThrow(
                    () -> new IllegalStateException("User not found")
            );

            //create token
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(user.getUserName(),token);

            return ResponseEntity.ok(loginRes);

            //return token
        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequest regReq) {

        //ensure all required fields are present
        if (regReq.getEmail() == null || regReq.getUserName() == null || regReq.getPassword() == null || regReq.getBusinessName() == null || regReq.getTelephoneNumber() == null || regReq.getCountry() == null || regReq.getPostCode() == null) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Missing required fields");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        //ensure username is unique on the request level
        if (customerRepository.findByUsername(regReq.getUserName()).isPresent()) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Username already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        //ensure email is unique on the request level
        if (customerRepository.findByEmail(regReq.getEmail()).isPresent()) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Email already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        //ensure password standard is met
        if (regReq.getPassword().length() < 8) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            Customer user = new Customer(regReq.getEmail(), regReq.getUserName(),encodePassword(regReq.getPassword()),regReq.getBusinessName(), regReq.getTelephoneNumber(), regReq.getCountry(), regReq.getPostCode());
            customerRepository.save(user);

            //create token
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(user.getUserName(),token);

            return ResponseEntity.ok(loginRes);
        }catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
