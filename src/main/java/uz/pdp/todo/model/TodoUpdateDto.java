package uz.pdp.todo.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TodoUpdateDto {

    private String title;

    private String description;
    private Boolean completed;
}
