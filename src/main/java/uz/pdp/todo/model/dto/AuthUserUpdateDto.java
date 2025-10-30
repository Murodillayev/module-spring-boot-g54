package uz.pdp.todo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUserUpdateDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String roleId;
}
