package uz.pdp.todo.model.dto.authUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUserCreateDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String roleId;
}
