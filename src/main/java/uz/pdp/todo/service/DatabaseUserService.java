package uz.pdp.todo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.todo.model.dto.UserDto;
import uz.pdp.todo.repository.DatabaseUserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DatabaseUserService {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final DatabaseUserRepository repository;

    public DatabaseUserService(DatabaseUserRepository repository) {
        this.repository = repository;
    }


    public List<UserDto> getAllForAgent(String agentId, Long version) {
        List<UserDto> resp = new ArrayList<>();
        List<Object[]> users = repository.getAllForAgent(agentId, version);

        users.forEach(user -> {
            String rolesJson = (String) user[5];
            List<String> rolesList = Collections.emptyList();

            if (rolesJson != null && !rolesJson.equals("null") && !rolesJson.trim().isEmpty()) {
                try {
                    rolesList = mapper.readValue(rolesJson, new TypeReference<>() {
                    });
                } catch (JsonProcessingException ignored) {
                }
            }

            log.info("ROLES_LIST ===============> {}", rolesList);
            resp.add(UserDto.builder()
                    .id(user[0].toString())
                    .username(user[1].toString())
                    .password(user[2].toString())
                    .deleted(Boolean.parseBoolean(user[3].toString()))
                    .version(user[4] instanceof Number ? ((Number) user[4]).intValue() : Integer.parseInt(user[4].toString()))
                    .roles(rolesList == null ? Collections.emptyList() : rolesList)
                    .build());
        });

        return resp;
    }
}
