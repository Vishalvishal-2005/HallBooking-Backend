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
     * <p>This method returns a {@link WebMvcConfigurer} that sets up CORS mappings to allow
     * requests from the specified frontend domain. It permits various HTTP methods and headers,
     * and allows credentials to be included in the requests.</p>
     *
     * <p>The following CORS settings are applied:</p>
     * <ul>
     *     <li>Allowed origins: "https://venuetrack.netlify.app"</li>
     *     <li>Allowed methods: GET, POST, PUT, DELETE, OPTIONS</li>
     *     <li>Allowed headers: All headers</li>
     *     <li>Allow credentials: true</li>
     * </ul>
     *
     * @return a {@link WebMvcConfigurer} instance with the configured CORS settings.
     *
     * @throws IllegalArgumentException if the allowed origins or methods are invalid.
     * @throws NullPointerException if the registry is null when attempting to add mappings.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
             *
             * This method adds CORS mappings to the provided {@link CorsRegistry} instance.
             * It allows requests from the specified frontend domain and supports various HTTP methods.
             *
             * @param registry the {@link CorsRegistry} to which the CORS mappings will be added
             *
             * @throws IllegalArgumentException if the provided registry is null
             *
             * <p>
             * The following CORS settings are applied:
             * <ul>
             *   <li>Allowed origins: "https://venuetrack.netlify.app"</li>
             *   <li>Allowed methods: GET, POST, PUT, DELETE, OPTIONS</li>
             *   <li>Allowed headers: all headers ("*")</li>
             *   <li>Credentials: allowed</li>
             * </ul>
             * </p>
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://venuetrack.netlify.app") // Allow frontend domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
