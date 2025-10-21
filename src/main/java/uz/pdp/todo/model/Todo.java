package uz.pdp.todo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
}
