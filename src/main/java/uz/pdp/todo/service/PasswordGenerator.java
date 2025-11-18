package uz.pdp.todo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordGenerator {
    public String generatePassword() {
        String uuidPassword = UUID.randomUUID().toString();
        return uuidPassword.substring((uuidPassword.length()/2)+2).replace("-", "");
    }
}
