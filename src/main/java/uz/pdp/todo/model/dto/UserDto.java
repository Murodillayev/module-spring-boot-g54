package uz.pdp.todo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private List<String> roles; // create_table,
    private Integer version;
    private boolean deleted;
}
