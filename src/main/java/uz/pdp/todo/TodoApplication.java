package uz.pdp.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TodoApplication {
    public static void main(String[] args) {

        SpringApplication.run(TodoApplication.class, args);
    }

}
