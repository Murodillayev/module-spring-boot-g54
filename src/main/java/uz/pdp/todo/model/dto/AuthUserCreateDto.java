package uz.pdp.todo.model.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.todo.model.entity.AuthRole;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUserCreateDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String userPhone;
    private String roleId;
}
