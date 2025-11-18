package uz.pdp.todo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uz.pdp.todo.events.UserCreateEvent;

@Service
@Slf4j
public class MvRefresherService {

    @SneakyThrows
    public void refreshUsersStatistic() {
        log.info("Start refresh usersStatistic mv");
        Thread.sleep(1000);
        log.info("Successfully refreshed users statistic");
    }
}
