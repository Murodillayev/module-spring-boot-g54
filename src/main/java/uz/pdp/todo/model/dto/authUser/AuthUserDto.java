package uz.pdp.todo.model.dto.authUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.todo.model.dto.IdNameDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private IdNameDto role;
}
