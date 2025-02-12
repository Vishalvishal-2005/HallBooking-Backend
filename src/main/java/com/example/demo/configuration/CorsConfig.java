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
     * <p>This method returns a {@link WebMvcConfigurer} that defines the CORS mappings.
     * It allows requests from the specified origin and permits various HTTP methods.</p>
     *
     * <p>The following CORS settings are applied:</p>
     * <ul>
     *   <li>Allowed origin patterns: "https://venuetrack.netlify.app"</li>
     *   <li>Allowed HTTP methods: GET, POST, PUT, DELETE, OPTIONS</li>
     *   <li>Allowed headers: All headers</li>
     *   <li>Credentials: Allowed</li>
     * </ul>
     *
     * @return a {@link WebMvcConfigurer} instance with the configured CORS settings.
     *
     * @throws IllegalArgumentException if the allowed origin patterns are invalid or
     *         if any of the allowed methods are not supported.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
             *
             * This method adds CORS mappings to the provided {@link CorsRegistry} instance.
             * It allows requests from the specified origin pattern, supports various HTTP methods,
             * and permits all headers. Additionally, it allows credentials to be included in the requests.
             *
             * @param registry the {@link CorsRegistry} to which the CORS mappings will be added
             *
             * @throws IllegalArgumentException if the provided registry is null
             * @throws UnsupportedOperationException if the CORS mappings cannot be modified
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("https://venuetrack.netlify.app")  // ✅ Corrected
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
