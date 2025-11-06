package uz.pdp.todo.senders.impl;


import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.todo.senders.MessageService;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService implements MessageService {

    private final JavaMailSender mailSender;

    @SneakyThrows
    @Override
    public void sendMessage(String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setSubject("Qarz masalasi");
        mimeMessage.setText("Assalomu alaykum");
        mimeMessage.setRecipients(Message.RecipientType.TO, "devolmoscrm@gmail.com");
        mailSender.send(mimeMessage);
    }
}
