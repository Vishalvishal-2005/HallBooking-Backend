package com.example.hallbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    /**
     * Creates and returns a new instance of {@link BCryptPasswordEncoder}.
     * <p>
     * This method is used to obtain a password encoder that uses the BCrypt hashing function.
     * It is commonly used for encoding passwords before storing them in a database.
     * </p>
     *
     * @return a new instance of {@link BCryptPasswordEncoder}
     * @throws IllegalArgumentException if any argument passed to the constructor of
     *         {@link BCryptPasswordEncoder} is invalid (not applicable in this case as
     *         the default constructor does not take any arguments).
     */
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    /**
     * Configures a {@link SecurityFilterChain} for HTTP security.
     *
     * This method sets up the security filter chain by disabling CSRF protection and
     * defining authorization rules for HTTP requests. Specifically, it permits all
     * requests to the sign-in and sign-up endpoints, while requiring authentication
     * for any other requests.
     *
     * @param http the {@link HttpSecurity} object used to configure security settings
     * @return a configured {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs while configuring the security settings
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/signin", "/api/users/signup").permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}
