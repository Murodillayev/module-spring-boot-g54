package uz.pdp.todo;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index() {
        return "index";
    }



    @SneakyThrows
    @Scheduled(fixedRate = 5000)
    public void sendNotify(){
        List<WebSocketSession> sessions = MessageHandler.sessions;
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage("Salom " + session.getRemoteAddress()));
        }
    }
}
