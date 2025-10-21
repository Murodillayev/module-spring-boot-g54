package uz.pdp.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser {

    @Id
    private String id;
    private String username;
    private String password;

}
