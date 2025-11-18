package uz.pdp.todo.events;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.service.EmailService;

@Component
@AllArgsConstructor
public class SendEmailListener {
   private final EmailService emailService;

    @EventListener(SendEmailEvent.class)
    public void sendEmail(String email) {
//        emailService.sendEmail();
    }
}
