package uz.pdp.todo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoCreateDto {

    private String title;

    private String description;
}


// TDD