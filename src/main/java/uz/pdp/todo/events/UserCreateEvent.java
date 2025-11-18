package uz.pdp.todo.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import uz.pdp.todo.AuthUser;

@Getter
public final class UserCreateEvent extends ApplicationEvent {

    private final AuthUser authUser;

    public UserCreateEvent(Object source, AuthUser authUser) {
        super(source);
        this.authUser = authUser;
    }



}


// immutable

// eop -> publishing and consuming

// consumer 1 (event)
// consumer 2 (event)
// consumer 3 (event)
