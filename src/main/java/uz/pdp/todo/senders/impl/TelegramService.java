package uz.pdp.todo.senders.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.todo.senders.MessageService;

@Slf4j
@Service
@Profile("telegram")
public class TelegramService implements MessageService {

    @Override
    @SneakyThrows
    @Async
    public void sendMessage(String message) {
        Thread.sleep(4000);
        log.info("Sending message to TELEGRAM : message => {}", message);

    }
}
