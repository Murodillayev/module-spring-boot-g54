package uz.pdp.todo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostRemove;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Todo {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
//    private LocalDateTime createdAt; // created_at
}
