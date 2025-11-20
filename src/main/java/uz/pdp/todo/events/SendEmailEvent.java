package uz.pdp.todo.events;

import org.springframework.context.ApplicationEvent;
import uz.pdp.todo.model.entity.AuthUser;
import uz.pdp.todo.model.entity.ProjectDatabase;

public final class SendEmailEvent extends ApplicationEvent {
    public final String userEmail;
    public final String userPassword;
    public final String userName;

    public SendEmailEvent(Object source, String userEmail, String userPassword, String userName) {
        super(source);
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
