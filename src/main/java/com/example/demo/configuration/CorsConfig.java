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
     * <p>This method returns a {@link WebMvcConfigurer} that allows all API requests
     * for debugging purposes. It permits all origins temporarily, which should be
     * changed to the specific frontend URL in a production environment.</p>
     *
     * <p>The CORS configuration allows the following HTTP methods:</p>
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     *     <li>PUT</li>
     *     <li>DELETE</li>
     *     <li>OPTIONS</li>
     * </ul>
     *
     * <p>All headers are allowed, and credentials are permitted.</p>
     *
     * @return a {@link WebMvcConfigurer} instance with the configured CORS settings.
     *
     * @throws IllegalArgumentException if the provided CORS settings are invalid.
     * @throws NullPointerException if the registry is null when attempting to add mappings.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
             * This method allows all API requests for debugging purposes and permits all origins temporarily.
             *
             * <p>
             * The following CORS settings are applied:
             * <ul>
             *   <li>All paths are allowed with the mapping "/**".</li>
             *   <li>All origins are allowed with the wildcard "*". This setting should be changed to the specific frontend URL in production.</li>
             *   <li>Allowed HTTP methods include GET, POST, PUT, DELETE, and OPTIONS.</li>
             *   <li>All headers are allowed with the wildcard "*".</li>
             *   <li>Credentials are allowed to be included in requests.</li>
             * </ul>
             * </p>
             *
             * @param registry the CorsRegistry object used to configure CORS settings.
             *
             * @throws IllegalArgumentException if the registry is null.
             * @throws UnsupportedOperationException if the CORS configuration cannot be applied due to unsupported settings.
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // ✅ Allow all API requests (for debugging)
                        .allowedOrigins("*")  // ✅ TEMPORARY: Allow all origins (Change to frontend URL later)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
