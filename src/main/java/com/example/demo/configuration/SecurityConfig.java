package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    /**
     * Creates a new instance of {@link PasswordEncoder} using the BCrypt hashing algorithm.
     *
     * @return a {@link PasswordEncoder} instance that uses BCrypt for password encoding.
     *
     * @throws IllegalStateException if the password encoder cannot be created due to
     *                               configuration issues or unsupported algorithms.
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
