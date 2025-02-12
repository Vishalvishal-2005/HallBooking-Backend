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
     * <p>
     * This method returns a {@link WebMvcConfigurer} that allows all endpoints to be accessed
     * from the specified origin, which is "https://venuetrack.netlify.app". It permits
     * the HTTP methods GET, POST, PUT, DELETE, and OPTIONS, and allows all headers.
     * Additionally, it enables the use of credentials such as cookies and authorization headers.
     * </p>
     *
     * @return a {@link WebMvcConfigurer} instance configured with CORS settings.
     *
     * @throws IllegalArgumentException if the allowed origins or methods are invalid.
     * @throws NullPointerException if the registry is null when attempting to add CORS mappings.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
             *
             * This method adds CORS mappings to the provided {@link CorsRegistry} instance.
             * It allows all endpoints, permits requests from the specified frontend origin,
             * and enables all HTTP methods and headers. Additionally, it allows credentials
             * to be included in the requests.
             *
             * @param registry the {@link CorsRegistry} to which CORS mappings will be added
             *
             * @throws IllegalArgumentException if the provided {@code registry} is null
             *
             * <p>
             * Example usage:
             * <pre>
             * CorsRegistry registry = new CorsRegistry();
             * addCorsMappings(registry);
             * </pre>
             * </p>
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("https://venuetrack.netlify.app") // Allow frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow cookies/auth
            }
        };
    }
}
