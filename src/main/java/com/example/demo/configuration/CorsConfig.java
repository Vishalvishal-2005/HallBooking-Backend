package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {
    @Bean
    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     *
     * <p>This method returns a {@link WebMvcConfigurer} that allows all origins,
     * HTTP methods (GET, POST, PUT, DELETE, OPTIONS), and headers. It also enables
     * credentials to be included in CORS requests. This configuration is intended
     * for debugging purposes and should be reviewed before deploying to production.</p>
     *
     * @return a {@link WebMvcConfigurer} instance with CORS mappings configured.
     *
     * @throws IllegalArgumentException if the provided CORS settings are invalid.
     * @throws NullPointerException if the registry is null when attempting to add mappings.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
             *
             * This method adds CORS mappings to the provided {@link CorsRegistry} instance.
             * It allows all origins, HTTP methods (GET, POST, PUT, DELETE, OPTIONS),
             * and headers, and enables credentials support. This configuration is intended
             * for debugging purposes and should be reviewed before deploying to production.
             *
             * @param registry the {@link CorsRegistry} to which the CORS mappings will be added
             *
             * @throws IllegalArgumentException if the provided {@code registry} is null
             * @throws UnsupportedOperationException if the CORS mappings cannot be modified
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")  // Allow all origins temporarily for debugging
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
