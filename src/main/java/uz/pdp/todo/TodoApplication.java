package uz.pdp.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.repository.AuthRoleRepository;
import uz.pdp.todo.repository.AuthUserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class TodoApplication {
    public static void main(String[] args) {

        SpringApplication.run(TodoApplication.class, args);
    }

    //    @Bean
    public CommandLineRunner runner(
            AuthUserRepository authUserRepository,
            AuthRoleRepository authRoleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            AuthUser authUser = new AuthUser();
            AuthRole role = new AuthRole();

            role.setCode("SUPER_ADMIN");
            role.setName("Super admin");
            authRoleRepository.save(role);

            authUser.setRole(role);
            authUser.setUsername("admin");
            authUser.setPassword(passwordEncoder.encode("123"));
            authUser.setPhone("998912123112");
            authUser.setEmail("test@gmail.com");
            authUser.setName("Muhammadkomil");
            authUserRepository.save(authUser);
        };
    }

}
