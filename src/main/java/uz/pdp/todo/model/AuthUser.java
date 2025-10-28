package uz.pdp.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUser {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
    private Boolean blocked;
}
