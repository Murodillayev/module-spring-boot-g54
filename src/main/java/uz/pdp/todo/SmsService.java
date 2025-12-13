package uz.pdp.todo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final RabbitTemplate rabbitTemplate;

    public SmsService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void send(String message) {

        rabbitTemplate.convertAndSend("todo_exchange", "todo_routing_key", message);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
