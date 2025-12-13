package uz.pdp.todo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final SmsService smsService;
    public final RabbitTemplate rabbitTemplate;

    public AuthService(SmsService smsService, RabbitTemplate rabbitTemplate) {
        this.smsService = smsService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void register(String data) {


        smsService.send("Successfully registered" + data);
        System.out.println("Successfully registered : " + data);
    }

}
