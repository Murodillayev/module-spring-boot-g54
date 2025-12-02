package uz.pdp.todo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthUserCommands {

    private final AuthUserDao dao;

    public AuthUserCommands(AuthUserDao dao) {
        this.dao = dao;
    }

    @ShellMethod
    public void login(
            @ShellOption(value = "u", valueProvider = UsernameValueProvider.class) String username,
            @ShellOption("p") String password
    ) {
        AuthUser authUser = dao.findByUsername(username);
        if (!authUser.getPassword().equals(password)) {
            System.out.println("Wrong password!");
            return;
        }
        SecurityContext.setCurrentUser(authUser);
        System.out.println("Successfully logged in!");

    }

    @ShellMethod
    public void logout() {
        SecurityContext.setCurrentUser(null);
        System.out.println("Bye 👋 !!!");

    }
}
