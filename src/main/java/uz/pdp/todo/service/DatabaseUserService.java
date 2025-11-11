package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.UserDto;
import uz.pdp.todo.repository.DatabaseUserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseUserService {
    private final DatabaseUserRepository repository;

    public DatabaseUserService(DatabaseUserRepository repository) {
        this.repository = repository;
    }

    public List<UserDto> getAllForAgent(String agentId, Long version) {

        List<UserDto> resp = new ArrayList<>();

        List<Object[]> users = repository.getAllForAgent(agentId, version);

        users.forEach(user -> {
            resp.add(UserDto.builder()
                    .id(user[0].toString())
                    .username(user[1].toString())
                    .password(user[2].toString())
                    .deleted(Boolean.parseBoolean(user[3].toString()))
                    .version(Long.parseLong(user[4].toString()))
                    .roles(Arrays.asList((String[]) user[5]))
                    .build());

        });
        return resp;
    }
}
