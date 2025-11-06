package uz.pdp.todo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeService {

    public void createUser(String userData) {

        log.info("Succesfully created: {}", userData);
    }
}
