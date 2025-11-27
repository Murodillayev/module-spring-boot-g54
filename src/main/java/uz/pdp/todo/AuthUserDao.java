package uz.pdp.todo;


import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthUserDao {
    private final List<AuthUser> USERS = new ArrayList<>();

    {
        AuthUser authUser = new AuthUser();
        authUser.setUsername("admin");
        authUser.setPassword("123");
        USERS.add(authUser);
    }

    public @NonNull AuthUser findByUsername(String username) {
        for (AuthUser authUser : USERS) {
            if (authUser.getUsername().equals(username)) {
                return authUser;
            }
        }
        throw new RuntimeException("User not found");
    }


    public List<AuthUser> findAll() {

        return  USERS;
    }
}
