package uz.pdp.todo.senders.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.todo.senders.MessageService;

@Service
@Slf4j
@Profile("sms")
public class SmsService implements MessageService {

    @Override
    @SneakyThrows
    @Async
    public void sendMessage(String message) {
        Thread.sleep(2000);
        log.info("Sending message to SMS : message => {}", message);
    }
}
