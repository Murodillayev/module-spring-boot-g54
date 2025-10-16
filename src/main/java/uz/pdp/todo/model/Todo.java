package uz.pdp.todo.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    private String id;
    private String title;
    private String description;
    private boolean completed;
}
