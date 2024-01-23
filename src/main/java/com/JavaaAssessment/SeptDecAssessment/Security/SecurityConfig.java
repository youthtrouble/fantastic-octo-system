package com.JavaaAssessment.SeptDecAssessment.Security;

import com.JavaaAssessment.SeptDecAssessment.Services.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is used to configure the security of the application.
 * <p>
 * This class is annotated with the {@link org.springframework.context.annotation.Configuration}
 * annotation to indicate that it is a configuration class.
 * </p>
 *
 * @see org.springframework.context.annotation.Configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminService customerService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
//    private final cors CorsFilter;

    public SecurityConfig(AdminService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.customerService = customUserDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
//        this.CorsFilter = corsFilter;
    }

    /**
     * This method is used to configure the authentication manager.
     * <p>
     * This method is used to configure the authentication manager.
     * It sets the user details service and password encoder for the authentication manager.
     * </p>
     *
     * @param http            The HttpSecurity object.
     * @param passwordEncoder The password encoder.
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customerService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * This method is used to configure the security filter chain.
     * <p>
     * This method is used to configure the security filter chain.
     * It sets the security filter chain to skip the authentication filter.
     * </p>
     *
     * @param http The HttpSecurity object.
     * @return SecurityFilterChain
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable()) //lambda expression
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                http.addFilterBefore(CorsFilter, UsernamePasswordAuthenticationFilter.class);
                http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    /**
     * This method is used to configure the password encoder.
     * <p>
     * This method is used to configure the password encoder.
     * It sets the password encoder to BCryptPasswordEncoder.
     * </p>
     *
     * @return PasswordEncoder
     */
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
