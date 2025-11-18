package uz.pdp.todo.events;

import org.springframework.context.ApplicationEvent;
import uz.pdp.todo.model.entity.ProjectDatabase;

public final class SendEmailEvent extends ApplicationEvent {
    public final String email;
    public SendEmailEvent(Object source, String email) {
        super(source);
        this.email = email;
    }
}
