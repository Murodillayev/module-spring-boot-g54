package uz.pdp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo.senders.MessageService;

@RestController
public class HomeController {

    private final MessageService mailService;

    @Autowired
    private HomeService service;

    public HomeController(MessageService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public String sendMessage() {
        service.createUser("user data");
        mailService.sendMessage("You successfully registered");
        return "Successfully sent";
    }
}
