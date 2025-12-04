package uz.pdp.todo.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
