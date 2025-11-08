package uz.pdp.todo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {
    private Integer userId;
    private String title;
    private Integer id;
    private Boolean completed;
}
