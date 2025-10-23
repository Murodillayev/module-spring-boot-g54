package uz.pdp.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class TodoApplication {
    public static void main(String[] args) {

        SpringApplication.run(TodoApplication.class, args);
    }

//    @Bean
    CommandLineRunner init(PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository) {
        return args -> {
            AuthUser authUser = new AuthUser();
            authUser.setId(UUID.randomUUID().toString());
            authUser.setUsername("admin");
            authUser.setPassword(passwordEncoder.encode("123"));
            authUserRepository.save(authUser);

        };
    }
}
