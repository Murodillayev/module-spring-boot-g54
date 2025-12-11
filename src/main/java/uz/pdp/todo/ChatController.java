package uz.pdp.todo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Controller
public class ChatController {


    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public MessageDto send(@Payload MessageDto message) {
        message.setSendTime(LocalDateTime.now());
        return message;
    }

}
