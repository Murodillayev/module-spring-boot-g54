package uz.pdp.todo.events;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.service.EmailService;

@Component
@AllArgsConstructor
public class SendEmailListener {
    private final EmailService emailService;

    @TransactionalEventListener()
    public void sendEmail(SendEmailEvent event) {
        emailService.sendEmail(event.userEmail, event.userName, event.userPassword);
    }
}
