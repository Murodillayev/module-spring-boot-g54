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
    public void sendEmail(String userEmail, String userName, String userPassword) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("User name and password for login");
        mailMessage.setText("Hey there!\nYou have been successfully registered to Hub!🤗🤗🤗\nHere are details for login: username =  %s and password = %s for login. DO NOT SHARE IT WITH ANYONE!!!".formatted(userName, userPassword));
        mailSender.send(mailMessage);
        log.info("Email sent successfully to email : {} ", userEmail);
    }
}
