package bg.softuni.mobilele.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // hashing algorithm for basic password protection.
        // slow execution, against bruteforce attacks
        return new Pbkdf2PasswordEncoder();
    }
}
