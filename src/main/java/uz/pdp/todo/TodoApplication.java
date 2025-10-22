package uz.pdp.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.todo.controller.SessionUser;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

//    @Bean
//    public AuditorAware<String> auditorAware() {
//        SessionUser sessionUser = new SessionUser();
//        return () -> Optional.ofNullable(sessionUser.getId());
//    }
}
