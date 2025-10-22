package uz.pdp.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.todo.model.dto.TodoInfo;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthUserDto {
    private String username;
    private String password;
    private List<TodoInfo> todos;
}
