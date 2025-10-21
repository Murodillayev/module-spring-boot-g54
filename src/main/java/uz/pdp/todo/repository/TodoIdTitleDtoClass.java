package uz.pdp.todo.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class TodoIdTitleDtoClass {
    private String id;
    private String title;


    public TodoIdTitleDtoClass(String id, String title) {
        this.id = id;


        this.title = title;
    }
}
