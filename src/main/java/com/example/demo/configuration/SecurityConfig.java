import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    /**
     * Creates a {@link PasswordEncoder} bean using BCrypt hashing.
     *
     * @return a {@link PasswordEncoder} instance that uses the BCrypt algorithm
     *
     * @throws IllegalStateException if the PasswordEncoder cannot be created due to
     *         configuration issues or if the BCrypt algorithm is not available.
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Create a PasswordEncoder bean
    }
}
