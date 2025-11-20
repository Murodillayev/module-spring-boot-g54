package uz.pdp.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.pdp.todo.model.entity.AuthRole;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.model.entity.DatabaseRole;
import uz.pdp.todo.repository.AuthRoleRepository;
import uz.pdp.todo.repository.AuthUserRepository;
import uz.pdp.todo.repository.DatabaseRoleRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

//        @Bean
    public CommandLineRunner runner(
            DatabaseRoleRepository databaseRoleRepository
    ) {
        return args -> {
            databaseRoleRepository.save(new DatabaseRole("project_select","PROJECT_SELECT","can select from tables but can't do CRUD"));
            databaseRoleRepository.save(new DatabaseRole("project_create","PROJECT_CREATE","can create but cannot do CRUD"));
            databaseRoleRepository.save(new DatabaseRole("project_crud","PROJECT_CRUD","can do CRUD but cannot create"));
        };
    }

}
