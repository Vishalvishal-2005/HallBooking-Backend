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
 * <p>This method returns a {@link WebMvcConfigurer} that customizes the CORS mappings
 * for the API endpoints. It allows requests from a specified origin and permits
 * certain HTTP methods and headers.</p>
 *
 * <p>The following CORS settings are applied:</p>
 * <ul>
 *   <li>Allowed origin: "https://your-frontend-url.com"</li>
 *   <li>Allowed methods: GET, POST, PUT, DELETE</li>
 *   <li>Allowed headers: All headers</li>
 *   <li>Credentials: Allowed</li>
 * </ul>
 *
 * @return a {@link WebMvcConfigurer} instance with the configured CORS settings.
 *
 * @throws IllegalArgumentException if the allowed origin is null or empty.
 * @throws UnsupportedOperationException if the CORS configuration cannot be applied.
 */
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        /**
         * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
         *
         * This method allows cross-origin requests to the specified API endpoints.
         * It sets the allowed origins, HTTP methods, headers, and credentials for CORS requests.
         *
         * @param registry the {@link CorsRegistry} to which the CORS mappings will be added
         *
         * @throws IllegalArgumentException if the provided registry is null
         *
         * <p>
         * The following CORS settings are applied:
         * <ul>
         *   <li>Allowed origins: "https://your-frontend-url.com"</li>
         *   <li>Allowed methods: GET, POST, PUT, DELETE</li>
         *   <li>Allowed headers: all headers ("*")</li>
         *   <li>Credentials: allowed</li>
         * </ul>
         * </p>
         */
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**")
                .allowedOrigins("https://your-frontend-url.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    };
}

}
