package uz.pdp.todo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uz.pdp.todo.events.UserCreateEvent;

@Service
@Slf4j
public class EmailService {

    @SneakyThrows
    public void sendMessage(String email, String message) {
        Thread.sleep(1500);
        log.info("Send email to {} with message {}", email, message);
    }
}
