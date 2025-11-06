package uz.pdp.todo.senders.impl;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.todo.senders.MessageService;

@Service
@Slf4j
@Profile("mail")
public class MailService implements MessageService {

    @SneakyThrows
    @Async
    @Override
    public void sendMessage(String message) {
        Thread.sleep(4000);
        log.info("Sending message to MAIL : message => {}", message);
        System.out.println();
    }

}
