package uz.pdp.todo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.entity.AuthUser;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(AuthUser authUser) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(authUser.getEmail());
        mailMessage.setSubject("User name and password for login");
        mailMessage.setText("your user name %s and %s for login. DO NOT SHARE IT WITH ANYONE!!!".formatted(authUser.getUsername(),authUser.getPassword()));
        mailSender.send(mailMessage);
        log.info("Email sent successfully to email : {} ", authUser.getEmail());
    }
}
